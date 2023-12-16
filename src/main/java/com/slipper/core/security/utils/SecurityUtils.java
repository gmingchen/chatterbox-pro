package com.slipper.core.security.utils;

import com.slipper.common.constant.Constant;
import com.slipper.common.utils.HttpContextUtils;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 安全服务工具
 * @author gumingchen
 */
public class SecurityUtils {

    /**
     * 获取 TOKEN
     * 先从 请求头中查找 如果不存在 从参数中查找
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(Constant.TOKEN_KEY);
        }
        return token;
    }

    /**
     * 获取 TOKEN
     * 先从 请求头中查找 如果不存在 从参数中查找
     * @return
     */
    public static String getToken() {
        return getToken(HttpContextUtils.getHttpServletRequest());
    }

    /**
     * 获取当前的认证信息
     * @return
     */
    public static Authentication getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .orElse(null);
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static LoginUserDTO getLoginUser() {
        return Optional.ofNullable(getAuthentication())
                .map(authentication -> authentication.getPrincipal() instanceof LoginUserDTO ? (LoginUserDTO) authentication.getPrincipal() : null)
                .orElse(null);

    }

    /**
     * 获取当前登录用户ID
     * @return
     */
    public static Long getLoginUserId() {
        return Optional.ofNullable(getLoginUser())
                .map(LoginUserDTO::getId)
                .orElse(null);
    }
   
    /**
     * 设置当前用户到上下文中
     * @param loginUser 用户
     * @param request 请求
     */
    public static void setLoginUser(LoginUserDTO loginUser, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser, null, null);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}
