package com.douzone.jblog.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.jblog.security.AuthInterceptor;
import com.douzone.jblog.security.LoginInterceptor;
import com.douzone.jblog.security.LogoutInterceptor;

@SpringBootConfiguration
@PropertySource("classpath:com/douzone/jblog/config/WebConfig.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env; 

	// MESSAGE

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

		registry.addInterceptor(loginInterceptor()).addPathPatterns(env.getProperty("security.auth-url"));

		registry.addInterceptor(logoutInterceptor()).addPathPatterns(env.getProperty("security.logout"));

		registry.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(env.getProperty("security.auth-url"))
				.excludePathPatterns(env.getProperty("security.logout"))
				.excludePathPatterns("/assets/**");
	}

	// Message Converters
	@Bean // string type : utf-8 설정(한글 되도록)
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));

		return messageConverter;
	}
	
	// file upload mapping
	// RESOURCE MAPPING (URL MAGIC MAPPING)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
				.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));

	}
}
