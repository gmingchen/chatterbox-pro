package com.slipper.core.netty.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gumingchen
 */
@Accessors(chain = true)
@Data
public class WsResponseDTO<T> implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;
    /**
     * 响应类型 0-心跳 1-聊天消息 2-ack确认 3-异常消息 4-好友申请 5-同意好友申请 6-拒绝好友申请 7-删除好友
     */
    private Integer type;
    /**
     * 消息
     */
    private String message;
    /**
     * 响应主要内容
     */
    private T body;
}
