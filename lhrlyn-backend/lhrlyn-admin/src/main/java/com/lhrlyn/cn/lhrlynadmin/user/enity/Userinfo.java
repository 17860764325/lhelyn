package com.lhrlyn.cn.lhrlynadmin.user.enity;

import lombok.Data;

import java.util.List;

@Data
public class Userinfo  {
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
}
