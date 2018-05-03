package com.zc.cris.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    // 不推荐为了访问到templates 文件夹下的index.html 而去特意写一个映射方法
    // html文件一般都是放在templates 文件夹下，因为可以使用 thymeleaf 模板引擎解析
    /*@RequestMapping({"/", "/index.html"})
    public String index(){
        return "index";
    }*/

    @ResponseBody
    @RequestMapping("/hello")
    public String say(){
        return "hello,cris!";
    }

    // classpath:/templates/success.html
    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("msg", "<h1>你好</h1>");
        map.put("users", Arrays.asList("cris", "张三", "米切尔"));
        return "success";
    }
}
