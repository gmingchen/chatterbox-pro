package com.slipper.core.netty.handler;

import cn.hutool.json.JSONUtil;
import com.slipper.common.constant.WebSocketConstant;
import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.core.netty.dto.WsResponseDTO;
import com.slipper.core.netty.service.NettyService;
import com.slipper.core.netty.utils.WebSocketUsers;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * WebSocketServer端处理器
 *
 * @author Loafer
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Slf4j
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * webSocketUrl
     */
    private String webSocketUrl;
    /**
     * Netty 业务功能
     */
    private NettyService nettyService;
    /**
     * 用于打开关闭握手
     */
    private WebSocketServerHandshaker socketServerHandShaker;

    /**
     * 构造方法
     * @param webSocketUrl webSocketUrl
     * @param nettyService Netty 业务功能
     */
    public WebSocketServerHandler(String webSocketUrl, NettyService nettyService) {
        this.webSocketUrl = webSocketUrl;
        this.nettyService = nettyService;
    }

    /**
     * 异常
     * @param channelHandlerContext channelHandlerContext
     * @param cause                 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        log.error("🤝[服务端捕捉异常]: {}", cause.getMessage());
        channelHandlerContext.close();
    }

    /**
     * 当客户端主动链接服务端的链接后，调用此方法
     * @param channelHandlerContext ChannelHandlerContext
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        log.info("🤝与客户端[{}]建立连接\n" +
                        "🤝[服务器当前在线人数]: {}"
                , channelHandlerContext.channel().remoteAddress()
                , WebSocketUsers.getUsers().size() + 1);
    }

    /**
     * 与客户端断开连接时 调用此方法
     * @param channelHandlerContext channelHandlerContext
     */
    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        log.info("🤝与客户端[{}]断开连接", channel.remoteAddress());
        // 从存储结构中移除通道，也可以用缓存来替代
        WebSocketUsers.remove(channel);
        // 关闭通道
        channel.close();
        // 更新用户在线状态
        LoginUserDTO loginUserDTO = channel.attr(WebSocketConstant.ATTRIBUTE_KEY).get();
        nettyService.updateOnline(loginUserDTO.getId(), UserOnlineStatusEnum.OFFLINE);
    }

    /**
     * 读完之后调用的方法
     * @param channelHandlerContext ChannelHandlerContext
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext){
        channelHandlerContext.flush();
    }

    /**
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        messageReceived(channelHandlerContext, o);
    }

    /**
     * 接收客户端发送的消息
     * @param channelHandlerContext ChannelHandlerContext
     * @param receiveMessage        消息
     */
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object receiveMessage) throws IOException {
        if (receiveMessage instanceof FullHttpRequest) {
            // 传统http接入 第一次需要使用http建立握手
            FullHttpRequest fullHttpRequest = (FullHttpRequest) receiveMessage;
            log.info("🤝[握手]: {}", fullHttpRequest.uri());
            // 握手
            httpRequestHandler(channelHandlerContext, fullHttpRequest);
            // 发送连接成功给客户端
            WsResponseDTO wsResponseDTO = new WsResponseDTO();
            wsResponseDTO.setType(0);
            wsResponseDTO.setMessage("success");
            String responseString = JSONUtil.toJsonStr(wsResponseDTO);
            channelHandlerContext.channel().write(new TextWebSocketFrame(responseString));
        } else if (receiveMessage instanceof WebSocketFrame) {
            // WebSocket接入
            WebSocketFrame webSocketFrame = (WebSocketFrame) receiveMessage;
            webSocketFrameHandler(channelHandlerContext, webSocketFrame);
        }
    }

    /**
     * 第一次握手 判断用户是否存在 验证token 是否过期
     * @param channelHandlerContext channelHandlerContext
     * @param req                   请求
     */
    private void httpRequestHandler(ChannelHandlerContext channelHandlerContext, FullHttpRequest req) {
        // 构造握手响应返回
        WebSocketServerHandshakerFactory wsFactory
                = new WebSocketServerHandshakerFactory(webSocketUrl, null, false);
        // 从连接路径中截取凭证
        String uri = req.uri();
        int i = uri.lastIndexOf("/");
        String token = uri.substring(i + 1);

        boolean bool = nettyService.validateToken(token);
        if(bool) {
            LoginUserDTO loginUserDTO = nettyService.queryUser(token);
            Channel connectChannel = channelHandlerContext.channel();
            // 加入在线用户
            WebSocketUsers.put(loginUserDTO.getId().toString(), connectChannel);
            // 管道绑定用户信息
            connectChannel.attr(WebSocketConstant.ATTRIBUTE_KEY).set(loginUserDTO);
            socketServerHandShaker = wsFactory.newHandshaker(req);
            if (socketServerHandShaker == null) {
                // 发送版本错误
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(connectChannel);
            } else {
                // 握手响应
                socketServerHandShaker.handshake(connectChannel, req);
                // 更新用户在线状态
                nettyService.updateOnline(loginUserDTO.getId(), UserOnlineStatusEnum.ONLINE);
            }
        } else {
            // 断开连接
            channelHandlerContext.close();
        }
    }

    /**
     * webSocket处理逻辑
     * @param channelHandlerContext channelHandlerContext
     * @param frame                 webSocketFrame
     */
    private void webSocketFrameHandler(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) throws IOException {
        Channel channel = channelHandlerContext.channel();
        // region 纯文本消息
        if (frame instanceof TextWebSocketFrame) {
            // 获取消息内容
            String message = ((TextWebSocketFrame) frame).text();
            nettyService.messageHandle(channel, message);
        }
        // region 二进制消息
        if (frame instanceof BinaryWebSocketFrame) {
//            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
//            ByteBuf content = binaryWebSocketFrame.content();
//            log.info("🤝[二进制数据]:{}", content);
//            final int length = content.readableBytes();
//            final byte[] array = new byte[length];
//            content.getBytes(content.readerIndex(), array, 0, length);
//            MessagePack messagePack = new MessagePack();
//            WebSocketMessageEntity webSocketMessageEntity = messagePack.read(array, WebSocketMessageEntity.class);
//            log.info("🤝[解码数据]: {}", webSocketMessageEntity);
//            WebSocketUsers.sendMessageToUser(webSocketMessageEntity.getAcceptName(), webSocketMessageEntity.getContent());
        }
    }
}
