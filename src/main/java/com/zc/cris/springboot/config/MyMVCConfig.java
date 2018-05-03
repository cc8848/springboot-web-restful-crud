package com.zc.cris.springboot.config;

import com.zc.cris.springboot.componenetUtil.MyLocaleResolver;
import com.zc.cris.springboot.componenetUtil.MyLoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


// 使用实现 WebMvcConfigurer 接口的方式来扩展SpirngMVC 的各种功能
// 注意java8 之后的接口拥有默认实现方法，所以不需要再继承中间适配器类了
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 自定义用户请求处理结果；直接做视图映射，没有必要再写controller中的空映射方法了
        registry.addViewController("/cris").setViewName("success");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main").setViewName("dashboard");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // SpringBoot 1.x 版本已经做好了静态资源的映射，这里就不再需要排除了
        registry.addInterceptor(new MyLoginHandlerInterceptor())
                .addPathPatterns("/**")
                // spring5.x 版本(springboot 2.x 版本)后需要对静态资源做出排除，否则还是会拦截
                .excludePathPatterns(Arrays.asList("/", "/index.html", "/user/login", "/asserts/**"));
    }

}
