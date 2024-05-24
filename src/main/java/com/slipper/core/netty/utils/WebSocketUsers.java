package com.slipper.core.netty.utils;

import cn.hutool.json.JSONUtil;
import com.slipper.core.netty.dto.WsResponseDTO;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * WebSocket用户集
 *
 * @author Slipper
 * @email 1240235512@qq.com
 */
@Slf4j
public class WebSocketUsers {
    /**
     * 用户集
     */
    private static final ConcurrentMap<String, Channel> USERS = PlatformDependent.newConcurrentHashMap();

    private static WebSocketUsers ourInstance = new WebSocketUsers();

    private WebSocketUsers() {
    }

    public static WebSocketUsers getInstance() {
        return ourInstance;
    }

    /**
     * 存储通道
     * @param key     唯一键
     * @param channel 通道
     */
    public static void put(String key, Channel channel) {
        USERS.put(key, channel);
    }

    /**
     * 移除通道
     * @param channel 通道
     * @return 移除结果
     */
    public static boolean remove(Channel channel) {
        String key = null;
        boolean b = USERS.containsValue(channel);
        if (b) {
            Set<Map.Entry<String, Channel>> entries = USERS.entrySet();
            for (Map.Entry<String, Channel> entry : entries) {
                Channel value = entry.getValue();
                if (value.equals(channel)) {
                    key = entry.getKey();
                    break;
                }
            }
        } else {
            return true;
        }
        return remove(key);
    }

    /**
     * 移除通道
     * @param key 键
     */
    public static boolean remove(String key) {
        Channel remove = USERS.remove(key);
        boolean containsValue = USERS.containsValue(remove);
        log.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                "\t🤝[移出结果]: {}\n" +
                "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓", containsValue ? "失败" : "成功");
        return containsValue;
    }

    /**
     * 获取在线用户列表
     * @return 返回用户集合
     */
    public static ConcurrentMap<String, Channel> getUsers() {
        return USERS;
    }

    /**
     * 群发消息
     * @param messageObj 消息
     */
    public static <T> void sendMessage(WsResponseDTO<T> messageObj) {
        String message = JSONUtil.toJsonStr(messageObj);
        Collection<Channel> values = USERS.values();
        for (Channel value : values) {
            value.write(new TextWebSocketFrame(message));
            value.flush();
        }
    }

    /**
     * 指定某些人发送消息
     * @param messageObj 消息
     * @param keys key数组
     */
    public static <T> void sendMessage(WsResponseDTO<T> messageObj, List<String> keys) {
        String message = JSONUtil.toJsonStr(messageObj);
        for (String key : keys) {
            Channel channel = USERS.get(key);
            if (channel != null) {
                channel.write(new TextWebSocketFrame(message));
                channel.flush();
            }
        }
    }

    /**
     * 指定某些人发送消息
     * @param messageObj 消息
     * @param key key
     */
    public static <T> void sendMessage(WsResponseDTO<T> messageObj, String key) {
        List<String> keys = new ArrayList<>();
        keys.add(key);
       sendMessage(messageObj, keys);
    }

}