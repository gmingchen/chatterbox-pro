package com.slipper.core.netty;

import com.slipper.core.netty.config.NettyConfig;
import com.slipper.core.netty.handler.WebSocketChildHandler;
import com.slipper.core.netty.service.NettyService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * webSocket服务器
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Slf4j
@Component
@Order(1)
public class WebSocketServer implements CommandLineRunner {
    /**
     * Netty配置类
     */
    @Resource
    private NettyConfig nettyConfig;

    @Resource
    private NettyService nettyService;

    /**
     * 启动服务端的方法
     * 推荐的线程数量计算公式：
     * 1. 线程数量 = （线程总时间/瓶颈资源时间） * 瓶颈资源的线程并行数
     * 2. QPS    = 1000/线程总时间 * 线程数
     * @param port         服务器监听的端口号
     * @param webSocketUrl webSocket url
     *
     * @throws Exception exception
     */
    @Async
    public void run(int port, String webSocketUrl) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketChildHandler(webSocketUrl, nettyService));
            Channel ch = bootstrap.bind(port).sync().channel();
            log.info("🤝[服务器启动端口]: {}\n", port);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Async
    @Override
    public void run(String... args) throws Exception {
        run(nettyConfig.getPort(), nettyConfig.getUrl());
    }
}
