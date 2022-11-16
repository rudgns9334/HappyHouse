package com.mycom.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.project.common.LoginInterceptor;

@Configuration 
public class WebMVCConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/", "/index.html", "/login.html", "/register.html")
			.excludePathPatterns("/login/**", "/register/**", "/css/**", "/js/**", "/images/**");
	}
	
}