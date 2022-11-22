package com.mycom.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.project.exception.UnAuthorizedException;
import com.mycom.project.login.service.JwtService;
import com.mycom.project.user.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final String HEADER_AUTH = "refresh-token";
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		System.out.println("LoginInterceptor : preHandle !!!");
//		return true;
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println(token);
		if(token != null && jwtService.checkToken(token)){
			return true;
		}else{
			throw new UnAuthorizedException();
		}	
	}
}
