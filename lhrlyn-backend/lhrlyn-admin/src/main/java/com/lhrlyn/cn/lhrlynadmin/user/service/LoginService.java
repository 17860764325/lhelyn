package com.lhrlyn.cn.lhrlynadmin.user.service;

import com.lhrlyn.cn.lhrlynadmin.user.dto.UserDto;
import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import com.lhrlyn.cn.lhrlynadmin.user.util.ResultData;

public interface LoginService {

    ResultData checkUser(UserDto user);
    ResultData getUserInfo(String token);
}
