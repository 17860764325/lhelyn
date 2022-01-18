package com.lhrlyn.cn.lhrlynadmin.user.enity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "role")
public class Role  {

    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_about")
    private String roleAbout;

    @Column(name = "is_void")
    private String isVoid;
}
