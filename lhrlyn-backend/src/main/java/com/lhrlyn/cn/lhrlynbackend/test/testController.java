package com.lhrlyn.cn.lhrlynbackend.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  测试
 * @author lhr
 * @date  2021/12/8 10:10 下午
 * @param
 * @return:
 */
@Controller
public class testController {

    @ResponseBody
    @RequestMapping("")
    public String test () {
        return "test success !";
    }
}
