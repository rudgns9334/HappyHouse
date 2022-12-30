package com.mycom.project.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserParamDto;
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
	private final int DUPLICATE = -2;

	@PostMapping(value="/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto dto) {
		System.out.println("!!!!!!!!!!!!!!!");
		System.out.println(dto);
		UserResultDto userResultDto = userService.userRegister(dto);

		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else if(userResultDto.getResult() == DUPLICATE) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/modify")
	public ResponseEntity<Map<String, String>> modify(@RequestBody UserDto dto) {

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
	
	@DeleteMapping(value="/withdraw/{userSeq}")
	public ResponseEntity<Map<String, String>> withdraw(@PathVariable int userSeq) {

		UserResultDto userResultDto = userService.userWithdraw(userSeq);
		Map<String, String> map = new HashMap<>();
		
		if (userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/users")
	private ResponseEntity<UserResultDto> userList(UserParamDto userParamDto) {
		UserResultDto userResultDto;

		if (userParamDto.getSearchWord() == null || userParamDto.getSearchWord().isEmpty()) {
			userResultDto = userService.userList(userParamDto);
		} else {
			userResultDto = userService.userListSearchWord(userParamDto);
		}
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/users/detail")
	private ResponseEntity<UserResultDto> userDetail(UserParamDto userParamDto) {
		
		UserResultDto userResultDto = userService.userDetail(userParamDto);
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="/users/{userSeq}")
	private ResponseEntity<UserResultDto> userUpdate(UserDto dto, MultipartHttpServletRequest request) {
		System.out.println("UPDATE");

		UserResultDto userResultDto = userService.userUpdate(dto, request);
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/users/{userSeq}")
	private ResponseEntity<UserResultDto> userDelete(@PathVariable int userSeq) {

		UserResultDto userResultDto = userService.userDelete(userSeq);
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/users")
	private ResponseEntity<UserResultDto> userInsert(UserDto userDto, MultipartHttpServletRequest request){

	    System.out.println(userDto);
	    
	    UserResultDto userResultDto = userService.userInsert(userDto, request);
	    
	    if( userResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}