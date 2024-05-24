package com.slipper.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("message")
public class MessageEntity extends BasePO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 类型：0-文本 1-图片 2-语音 3-其它文件
     */
    private Integer type;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 图片资源路径
     */
    private String image;
    /**
     * 语音资源路径
     */
    private String voice;
    /**
     * 文件资源路径
     */
    private String file;
    /**
     * 状态：0-撤回 1-正常
     */
    private Integer status;
}
