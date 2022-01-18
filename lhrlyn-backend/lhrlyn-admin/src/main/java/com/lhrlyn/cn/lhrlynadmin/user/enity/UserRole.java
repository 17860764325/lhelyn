package com.lhrlyn.cn.lhrlynadmin.user.enity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_roles")
public class UserRole  {

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long UserId;

    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "is_void")
    private String isVoid;

}
