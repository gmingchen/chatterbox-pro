package com.slipper.modules.user.service;

import com.slipper.common.enums.StatusEnum;
import com.slipper.common.enums.UserOnlineStatusEnum;
import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.captcha.model.req.CaptchaReqVO;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.UserBaseDTO;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.dto.UserInfoDTO;
import com.slipper.modules.user.model.req.UserInfoReqVO;
import com.slipper.modules.user.model.req.UserSearchReqVO;
import com.slipper.modules.user.model.req.UserUpdateEmailReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserSearchResVO;

import java.util.List;

/**
 * 用户
 * @author gumingchen
 */
public interface UserService extends IServiceX<UserEntity> {

    /**
     * 新增用户
     * @param dto 新增参数
     * @return
     */
    Long create(UserCreateDTO dto);

    /**
     * 通过用户ID查询信息
     * @param reqVO 参数
     * @return
     */
    UserInfoDTO queryById(UserInfoReqVO reqVO);

    /**
     * 通过邮箱查询用户
     * @param email 邮箱
     * @return
     */
    UserEntity queryByEmail(String email);

    /**
     * 更新基础信息
     * @param reqVO 更新参数
     */
    void update(UserUpdateReqVO reqVO);

    /**
     * 获取更新邮箱验证码
     * @param reqVO 验证码参数
     */
    void updateEmailCaptcha(CaptchaReqVO reqVO);
    /**
     * 更新邮箱
     * @param reqVO 更新邮箱参数
     */
    void updateEmail(UserUpdateEmailReqVO reqVO);

    /**
     * 更新用户在线状态
     * @param id 用户ID
     * @param online 在线状态枚举
     */
    void updateOnline(Long id, UserOnlineStatusEnum online);

    /**
     * 通过昵称|邮箱搜索用户 不包括当前用户
     * @param reqVO 搜索参数
     * @return
     */
    PageResult<UserSearchResVO> queryByNicknameOrEmail(UserSearchReqVO reqVO);

}
