package com.lhrlyn.cn.lhrlynadmin.user.util.tokenJWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 *  token拦截器设置
 * @author lhr
 * @date  2021/12/30 11:02 PM
 * @param
 * @return:
 */
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String ,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        String token = request.getHeader("token");
        log.info("token->>" + token);
        Map<String,Object> map = new HashMap<>();
        /**
         *  判断token格式正确性
         */
        if (JwtUtil.verify(token) == 0) {
            log.info("token格式不正确");
            return false;
        } else {
            Long expire = redisTemplate.getExpire(token);
            if (expire > 0) {
                log.info("token验证通过,放行");
                redisTemplate.expire(token,30L, TimeUnit.MINUTES);
                return true;
            } else {
                log.info("toekn已经过期");
                return false;
            }
        }
    }
}
