package com.lhrlyn.cn.lhrlynadmin.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long userid;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String isVoid;
    private String token;
}
