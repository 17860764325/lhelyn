package com.lhrlyn.cn.lhrlynadmin.user.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class loginController {

    @PostMapping("/login")
    public String login () {
        return "login success!";
    }


}
