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

    MAIL_SEND_ERROR(4000, "邮件发送失败，请稍后再试！"),
    CAPTCHA_ERROR(4001, "验证码错误！"),
    REGISTER_USER_DUPLICATE(4002, "该用户已存在，请勿重复注册！"),
    LOGIN_USER_NOT_EXIST(4003, "该用户不存在，请先完成注册！"),
    USER_NOT_EXIST(4004, "该用户不存在！"),
    USER_DISABLED(4005, "账号已被封，请联系管理员！"),
    NOT_LOGIN(4006, "还未登录，请先登录！"),
    TOKEN_EXPIRE(4007, "凭证已过期，请重新登录！"),
    GROUPING_NOT_EXIST(4008, "该分组不存在，请先重新选择！"),
    APPLY_DUPLICATE(4009, "申请已提交，请勿重复申请！"),
    APPLY_FRIEND_BOTH(4010, "已经互为好友，请勿重复申请！"),
    APPLY_REVIEW_DUPLICATE(4011, "申请已审核，请勿重复审核！"),
    ORIGINAL_EMAIL_ERROR(4012, "原邮箱不正确！"),
    EMAIL_IS_EXIST(4013, "该邮箱已存在！"),
    APPLY_SELF_FRIEND(4014, "不可以申请自己为好友！"),
    CONVERSATION_SELF_ERROR(4015, "不可以与自己新增会话！"),
    NOT_FRIEND(4016, "不是好友关系！"),
    NOT_ROOM_MEMBER(4017, "不是房间成员！"),
    IS_ROOM_MEMBER(4018, "已经是房间成员！"),
    FILE_UPLOAD_ERROR(4019, "文件上传失败！"),
    FILE_SIZE_EXCEED(4020, "文件大小超出限制！"),
    FILE_TYPE_ERROR(4021, "文件类型不正确"),








    DUPLICATE_KEY(4102, "数据库中已存在该记录！"),
    BAD_SQL(4103, "SQL异常！"),
    DATA_INTEGRITY_VIOLATION(4104, "数据完整性异常！"),
    NOT_EXIST(4104, "该记录不存在！"),
    EXISTED_ERROR(4105, "已存在该记录！"),
    RESUBMIT(4106, "操作太频繁,请稍后重试！"),
    NON_FRIEND(4107, "对方不是您好友！"),
    GROUP_EXIST_FRIEND(4107, "分组内还存在好友！"),
    FRIEND_EACH_OTHER(4108, "已经是好友，请勿重复申请！"),
    DUPLICATE_FRIEND_APPLY(4110, "请勿重复申请！"),
    WECHAT_REQUEST_ERROR(4111, "请求微信API异常！"),
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
