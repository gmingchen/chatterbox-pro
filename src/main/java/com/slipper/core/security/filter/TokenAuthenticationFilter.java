package com.slipper.core.security.filter;

import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.auth.service.AuthService;
import com.slipper.modules.user.model.dto.LoginUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Token 过滤器 验证token
 * 验证通过 将用户信息存入 Security 上下文
 * @author gumingchen
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 将用户信息放入上下文中
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotBlank(token) && authService.validateToken(token)) {
            Optional.ofNullable(authService.queryLoginUser(token))
                    .ifPresent(loginUser -> SecurityUtils.setLoginUser(loginUser, request));
        }
        // 模拟用户登录
        SecurityUtils.setLoginUser(new LoginUserDTO().setId(1L), request);
        filterChain.doFilter(request, response);
    }
}
