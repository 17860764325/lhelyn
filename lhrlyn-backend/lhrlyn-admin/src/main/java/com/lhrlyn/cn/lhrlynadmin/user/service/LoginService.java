package com.lhrlyn.cn.lhrlynadmin.user.service;

import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /**
     * 校验用户账号密码正确性
     *
     * @param
     * @author lhr
     * @date 2021/12/30 11:09 PM
     * @return:
     */
    public User checkUser(User user) {
        User user1 = new User();
        user1.setUserid(1);
        user1.setUsername("admin");
        user1.setPassword("123456");
        if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())) {
            return user1;
        }
        return new User();
    }
}
