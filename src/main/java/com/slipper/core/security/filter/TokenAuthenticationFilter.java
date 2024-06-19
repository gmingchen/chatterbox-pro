package com.slipper.core.security.filter;

import com.slipper.core.security.utils.SecurityUtils;
import com.slipper.modules.auth.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        List<String> ignores = new ArrayList<>();
        ignores.add("/auth/captcha/register");
        ignores.add("/auth/register");
        ignores.add("/auth/captcha/login");
        ignores.add("/auth/login");
        ignores.add("/file/upload/avatar");
        ignores.add("/auth/login/qq");

        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        if (!ignores.contains(uri)) {
            // 将用户信息放入上下文中
            String token = SecurityUtils.getToken(request);
            if (StringUtils.isNotBlank(token) && Boolean.TRUE.equals(authService.validateToken(token))) {
                Optional.ofNullable(authService.queryUser(token))
                        .ifPresent(loginUser -> SecurityUtils.setLoginUser(loginUser, request));
            }
        }
        filterChain.doFilter(request, response);
    }
}
