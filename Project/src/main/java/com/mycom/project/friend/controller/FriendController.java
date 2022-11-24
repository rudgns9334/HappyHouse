package com.mycom.project.friend.controller;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value="/list")
	private ResponseEntity<FriendResultDto> friendListSearch(@RequestParam("searchWord") String searchWord, @RequestParam("sendSeq") int sendSeq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setSearchWord(searchWord);
		friendParamDto.setSendSeq(sendSeq);
		friendResultDto = friendService.friendListSearchWord(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/list/{seq}")
	private ResponseEntity<FriendResultDto> friendList(@PathVariable int seq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setSeq(seq);
		friendResultDto = friendService.friendList(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/detail/{seq}")
	private ResponseEntity<FriendResultDto> friendDetail(@PathVariable int seq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setSeq(seq);
		friendResultDto = friendService.friendDetail(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/request")
	private ResponseEntity<FriendResultDto> friendRequest(@RequestBody FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto;

		friendResultDto = friendService.friendRequest(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete")
	private ResponseEntity<FriendResultDto> friendDelete(@RequestParam("sendSeq") int sendSeq, @RequestParam("receiveSeq") int receiveSeq) {
		FriendResultDto friendResultDto;

		FriendParamDto friendParamDto = new FriendParamDto();
		friendParamDto.setSendSeq(sendSeq);
		friendParamDto.setReceiveSeq(receiveSeq);
		friendResultDto = friendService.friendDelete(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/accept")
	private ResponseEntity<FriendResultDto> friendAccept(@RequestBody FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto;

		System.out.println("accept" + friendParamDto);
		friendResultDto = friendService.friendAccept(friendParamDto);
		
		if (friendResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
