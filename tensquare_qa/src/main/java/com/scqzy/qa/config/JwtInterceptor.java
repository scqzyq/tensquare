package com.scqzy.qa.config;

import exception.AuthUnsatisfyException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 10:52
 */
@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                String roles = String.valueOf(claims.get("roles"));

                if (StringUtils.isNotBlank(roles) || "admin".equals(roles)) {
                    request.setAttribute("claims_admin",token);
                }
                if (StringUtils.isNotBlank(roles) || "user".equals(roles)) {
                    request.setAttribute("claims_user",token);
                }
            } catch (Exception e) {
                throw new AuthUnsatisfyException("token不正确");
            }
        }
        return true;
    }
}
