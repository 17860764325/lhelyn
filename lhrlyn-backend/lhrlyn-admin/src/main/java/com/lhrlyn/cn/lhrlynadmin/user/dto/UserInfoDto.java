package com.lhrlyn.cn.lhrlynadmin.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
}
