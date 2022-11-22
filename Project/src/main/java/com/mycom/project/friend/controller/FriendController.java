package com.mycom.project.friend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.project.friend.dto.FriendParamDto;
import com.mycom.project.friend.dto.FriendResultDto;
import com.mycom.project.friend.service.FriendService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
@RequestMapping(value="/friends")
public class FriendController {

	@Autowired
	FriendService friendService;
	
	private final int SUCCESS = 1;
	
	@GetMapping(value="/list/{mySeq}")
	private ResponseEntity<FriendResultDto> friendList(@PathVariable int mySeq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setMySeq(mySeq);
		friendResultDto = friendService.friendList(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/detail/{mySeq}/{friendSeq}")
	private ResponseEntity<FriendResultDto> friendDetail(@PathVariable int mySeq, @PathVariable int friendSeq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setMySeq(mySeq);
		friendParamDto.setFriendSeq(friendSeq);
		friendResultDto = friendService.friendDetail(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/register")
	private ResponseEntity<FriendResultDto> friendRegister(@RequestBody FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto;

		friendResultDto = friendService.friendRegister(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete/{mySeq}/{friendSeq}")
	private ResponseEntity<FriendResultDto> friendDelete(@PathVariable int mySeq, @PathVariable int friendSeq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setMySeq(mySeq);
		friendParamDto.setFriendSeq(friendSeq);
		friendResultDto = friendService.friendDelete(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
