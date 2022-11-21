package com.mycom.project.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserResultDto;
import com.mycom.project.user.service.UserService;



@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController {
	@Autowired
	UserService userService;
	
	private final int SUCCESS = 1;

	@PostMapping(value="/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody UserDto dto) {
		System.out.println("!!!!!!!!!!!!!!!");
		System.out.println(dto);
		UserResultDto userResultDto = userService.userRegister(dto);
		Map<String, String> map = new HashMap<>();
		
		if (userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/modify")
	public ResponseEntity<Map<String, String>> modify(UserDto dto) {

		System.out.println(dto);
		UserResultDto userResultDto = userService.userModify(dto);
		Map<String, String> map = new HashMap<>();
		
		if (userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/withdraw")
	public ResponseEntity<Map<String, String>> withdraw(UserDto dto) {

		System.out.println(dto);
		UserResultDto userResultDto = userService.userWithdraw(dto);
		Map<String, String> map = new HashMap<>();
		
		if (userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
}