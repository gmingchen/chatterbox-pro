package com.slipper.modules.user.service;

import com.slipper.common.pojo.PageResult;
import com.slipper.core.mybatisplus.expend.service.IServiceX;
import com.slipper.modules.user.entity.UserEntity;
import com.slipper.modules.user.model.dto.UserCreateDTO;
import com.slipper.modules.user.model.req.UserOnlineReqVO;
import com.slipper.modules.user.model.req.UserPageReqVO;
import com.slipper.modules.user.model.req.UserUpdateReqVO;
import com.slipper.modules.user.model.res.UserPageResVO;

/**
 * 用户
 * @author gumingchen
 */
public interface UserService extends IServiceX<UserEntity> {

    /**
     * 分页
     * @param reqVO 参数
     * @return
     */
    PageResult<UserPageResVO> page(UserPageReqVO reqVO);

    /**
     * 编辑资料
     * @param reqVO 参数
     */
    void update(UserUpdateReqVO reqVO);

    /**
     * 编辑在线状态
     * @param reqVO 参数
     */
    void online(UserOnlineReqVO reqVO);

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * 新增
     * @param dto 参数
     * @return
     */
    UserEntity create(UserCreateDTO dto);

    /**
     * 更新在线状态
     * @param id ID
     * @param online 是否在线：0-离线 1-在线
     */
    void updateOnline(Long id, Integer online);

    /**
     * 更新帐号状态
     * @param id ID
     * @param status 状态：0-禁用 1-启用
     */
    void updateStatus(Long id, Integer status);

    /**
     * 通过OpenId查询用户信息
     * @param openId 微信OpenId
     * @return
     */
    UserEntity queryByOpenId(String openId);

}
