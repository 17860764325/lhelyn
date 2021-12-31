package com.lhrlyn.cn.lhrlynadmin.user.enity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer userid;
    private String username;
    private String password;
    private String token;
}
