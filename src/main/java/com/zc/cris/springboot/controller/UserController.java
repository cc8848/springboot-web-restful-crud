package com.zc.cris.springboot.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    //  @RequestMapping(value = "/user/login", method = RequestMethod.POST)  不推荐使用这种方式了
    // 推荐使用这种更加简便的方式完成 restful 风格的映射
    //    @GetMapping
    //    @DeleteMapping
    //    @PutMapping
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (StringUtils.isNotEmpty(userName) && StringUtils.equals("123456", password)) {
            // 登录成功，将登录信息保存到session 中，然后使用重定向，映射规则定义在我们的自定义mvc配置类中
            session.setAttribute("loginUser", userName);
            return "redirect:/main";
        } else {
            map.put("msg", "用户名密码错误");
            return "index";
        }
    }
}
