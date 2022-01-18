package com.lhrlyn.cn.lhrlynadmin.user.enity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "username")
    private String username;
    @Column(name = "phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_void")
    private String isVoid;

}
