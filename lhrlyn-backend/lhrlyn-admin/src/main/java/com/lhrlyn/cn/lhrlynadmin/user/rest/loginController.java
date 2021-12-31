package com.lhrlyn.cn.lhrlynadmin.user.rest;

import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import com.lhrlyn.cn.lhrlynadmin.user.service.LoginService;
import com.lhrlyn.cn.lhrlynadmin.user.util.redis.RedisUtils;
import com.lhrlyn.cn.lhrlynadmin.user.util.token.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;

@Slf4j
@RestController
public class loginController extends Controller {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录测试
     * http://127.0.0.1:8080/lhrlyn/admin/user/login
     *
     * @param
     * @author lhr
     * @date 2021/12/26 9:19 下午
     * @return: java.lang.String
     */
    @PostMapping("/user/login")
    public User login(@RequestBody  User user) {
        User user1 = loginService.checkUser(user);
        log.info(user1.toString());
        if (StringUtils.isEmpty(user1)) {
            // 生成token
            String token = JwtUtil.sign(user1.getUserid());
            redisTemplate.opsForValue().set(token, user1, Duration.ofMinutes(30L));
            user1.setToken(token);
            return user1;
        }
        User user2 = new User();
        user2.setToken("err");
        return user2;
    }

    /**
     * 根据token返回用户数据
     *
     * @param request
     * @return
     */
    @GetMapping("getUserOfLogin")
    public Object getUserOfLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object user = redisTemplate.opsForValue().get(token);//封装到redis里
        if (user != null) {
            return user;
        } else {
            return new User();
        }

    }
        @GetMapping("/redis/test")
        public String redisTest (String key, String value){
            redisUtils.set(key, value);
            return redisUtils.get(key).toString();
        }


    }
