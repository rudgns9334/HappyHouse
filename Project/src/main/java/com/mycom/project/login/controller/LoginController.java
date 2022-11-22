package com.mycom.project.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mycom.project.login.service.JwtService;
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
	private JwtService jwtService;
	
	@Autowired
	LoginService loginService;
	
	private final int SUCCESS = 1;
	
	@PostMapping(value="/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserDto dto) {
		UserDto userDto = loginService.login(dto);
		Map<String, String> map = new HashMap<>();
		System.out.println(userDto);
		if (userDto != null) {
			
			String accessToken = jwtService.createAccessToken("userSeq", userDto.getUserSeq());// key, data
			String refreshToken = jwtService.createRefreshToken("userSeq", userDto.getUserSeq());// key, data
			userDto.setToken(refreshToken);
			loginService.saveRefreshToken(userDto);
			map.put("access-token", accessToken);
			map.put("refresh-token", refreshToken);
			
			Gson gson = new Gson();
			String jsonStr = gson.toJson(userDto);
			
			map.put("userDto",jsonStr);
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		
		map.put("result", "fail");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/logout/{userSeq}")
	public ResponseEntity<Map<String, String>> logout(@PathVariable int userSeq){
		Map<String, String> map = new HashMap<>();
		if(loginService.deleteRefreshToken(userSeq) == 1) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/check")
	public ResponseEntity<Map<String, String>> check(HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, String> map = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			map.put("result", "success");
			status = HttpStatus.ACCEPTED;
		} else {
			map.put("result", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@PostMapping(value="/refresh")
	public ResponseEntity<Map<String, String>> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request){
		
		Map<String, String> map = new HashMap<>();
		String token = request.getHeader("refresh-token");
		if(jwtService.checkToken(token)) {
			if(token.equals(loginService.getRefreshToken(userDto.getUserSeq()))) {
				String accessToken = jwtService.createAccessToken("userSeq", userDto.getUserSeq());
				map.put("access-token", accessToken);
				map.put("result", "success");
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			}
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.UNAUTHORIZED);
		}
	}
}
