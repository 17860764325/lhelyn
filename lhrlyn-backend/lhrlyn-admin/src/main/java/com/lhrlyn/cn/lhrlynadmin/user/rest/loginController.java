package com.lhrlyn.cn.lhrlynadmin.user.rest;

import com.lhrlyn.cn.lhrlynadmin.user.dto.UserDto;
import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import com.lhrlyn.cn.lhrlynadmin.user.enity.Userinfo;
import com.lhrlyn.cn.lhrlynadmin.user.service.impl.LoginServiceImpl;
import com.lhrlyn.cn.lhrlynadmin.user.util.ResultData;
import com.lhrlyn.cn.lhrlynadmin.user.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class loginController extends Controller {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LoginServiceImpl loginServiceImpl;

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
    public ResultData login(@RequestBody UserDto user) {
        return loginServiceImpl.checkUser(user);
    }

    /**
     *  获取用户信息，包括权限和一些基本信息
     * @author lhr
     * @date  2022/1/17 9:07 下午
     * @param
     * @return: com.lhrlyn.cn.lhrlynadmin.user.enity.Userinfo
     */
    @GetMapping("/user/info")
    public ResultData<Userinfo> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        return loginServiceImpl.getUserInfo(token);
    }


    @GetMapping("/redis/test")
    public String redisTest(String key, String value) {
        redisUtils.set(key, value);
        return redisUtils.get(key).toString();
    }
}
