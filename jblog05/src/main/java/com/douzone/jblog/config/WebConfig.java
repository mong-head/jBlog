package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileuploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration //java 설정 파일인 경우 항상 넣기
@EnableAspectJAutoProxy //auto-proxy (aop)
@ComponentScan({"com.douzone.jblog.controller","com.douzone.jblog.exception"})
@Import({MvcConfig.class, MessageConfig.class,FileuploadConfig.class,SecurityConfig.class})
public class WebConfig {

}
