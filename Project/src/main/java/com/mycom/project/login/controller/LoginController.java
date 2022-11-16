package com.mycom.project.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mycom.project.login.service.LoginService;
import com.mycom.project.user.dto.UserDto;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	private final int SUCCESS = 1;
	
	@PostMapping(value="/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserDto dto, HttpSession session) {
		UserDto userDto = loginService.login(dto);
		Map<String, String> map = new HashMap<>();
		System.out.println(userDto);
		if (userDto != null) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson(userDto);
			session.setAttribute("userDto", userDto);
			map.put("userDto",jsonStr);
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		
		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}
}
