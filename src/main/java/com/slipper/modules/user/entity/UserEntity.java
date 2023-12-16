package com.slipper.modules.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.slipper.common.po.BasePO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户
 * @author gumingchen
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 是否在线：0-离线 1-在线
     */
    private Integer online;
    /**
     * 状态：0-禁用 1-启用
     */
    private Integer status;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 最后 上|下 线时间
     */
    private LocalDateTime lastAt;
}
