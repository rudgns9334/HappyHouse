package com.mycom.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.project.user.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		System.out.println("LoginInterceptor : preHandle !!!");
		
		// CORS 에서 put, delete 등 오류 해결 코드
		if( request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		// 이전에는 동기와 비동기가 넘어왓기에 그걸 구분해야했는데
		// 이번엔 어짜피 index.html뿐이고 모든 통신이 비동기로 이루어지기에 
		// 조건 따질 필요없이 결과를 보내주면 된다.
		if(userDto == null) {
			Gson gson = new Gson();
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			return false;
		}
		return true;
	}
}
