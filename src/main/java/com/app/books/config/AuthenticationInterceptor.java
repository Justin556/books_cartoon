package com.app.books.config;

import com.app.books.entity.User;
import com.app.books.service.UserService;
import com.app.books.utils.RedisUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 执行认证
        String token = request.getHeader("token");  // 从 http 请求头中取出 token
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        if (!redisUtil.hasKey(token)) {
            throw new RuntimeException("token无效，请重新登录");
        }
        return true;
    }
}
