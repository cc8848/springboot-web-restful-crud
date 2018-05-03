package com.zc.cris.springboot.componenetUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际化解析器，根据前台用户点击中英切换按钮带来的请求参数来进行页面语言的切换
 */
public class MyLocaleResolver implements LocaleResolver {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        // 处理方式一：获取到系统默认的语言和国家信息
        Locale locale = Locale.getDefault();
        // 处理方式二：根据用户请求头的信息来确定locale对象
        String header = request.getHeader("Accept-Language");
        String substring = header.substring(0, 2);
        switch (substring){
            case "en": {
                locale = new Locale("en", "US");
                break;
            }
            case "zh": {
                locale = new Locale("zh", "CN");
                break;
            }
        }

        if(StringUtils.isNotEmpty(l)){
            String[] split = l.split("-");
            locale = new Locale(split[0], split[1]);
        }

//        logger.info(request.getHeader("Accept-Language"));

        return locale;

    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
