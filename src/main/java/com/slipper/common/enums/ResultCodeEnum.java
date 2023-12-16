package com.slipper.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应结果状态码枚举
 * @author gumingchen
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    /**
     * 状态码
     */
    SUCCESS(0, "成功！"),
    ERROR(500, "系统异常，请联系管理员！"),
    VERIFICATION_ERROR(400, "参数校验异常！"),
    NOT_ALLOWED(401, "没有权限访问！"),
    NOT_FOUND(404, "路径不存在！"),
    METHOD_ERROR(405, "不支持该请求方法！"),
    NOT_LOGIN(4000, "还未登录，请先登录！"),
    TOKEN_EXPIRE(4001, "凭证已过期，请重新登录！"),
    DUPLICATE_KEY(4002, "数据库中已存在该记录！"),
    BAD_SQL(4003, "SQL异常！"),
    DATA_INTEGRITY_VIOLATION(4004, "数据完整性异常！"),
    NOT_EXIST(4004, "该记录不存在！"),
    EXISTED_ERROR(4005, "已存在该记录！"),
    RESUBMIT(4006, "操作太频繁,请稍后重试！"),
    NON_FRIEND(4007, "对方不是您好友！"),
    GROUP_EXIST_FRIEND(4007, "分组内还存在好友！"),
    FRIEND_EACH_OTHER(4008, "已经是好友，请勿重复申请！"),
    USER_NOT_EXIST(4009, "该用户不存在！"),
    DUPLICATE_FRIEND_APPLY(4010, "请勿重复申请！"),
    ;

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态信息
     */
    private final String message;
}
