package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.jblog.security.AuthInterceptor;
import com.douzone.jblog.security.LoginInterceptor;
import com.douzone.jblog.security.LogoutInterceptor;

@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

	// Interceptors
	// 1. login 
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	// 2. logout
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	// 3. auth
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	// mapping (경로) and exclude-mapping
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor())
				.addPathPatterns("/user/auth"); 
		
		registry.addInterceptor(logoutInterceptor())
				.addPathPatterns("/user/logout");
		
		registry.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/assets/**");
	}

}
