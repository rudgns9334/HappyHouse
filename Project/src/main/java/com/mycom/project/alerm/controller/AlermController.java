package com.mycom.project.alerm.controller;

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

import com.mycom.project.alerm.dto.AlermDto;
import com.mycom.project.alerm.dto.AlermResultDto;
import com.mycom.project.alerm.service.AlermService;
import com.mycom.project.bookmark.dto.BookMarkDto;
import com.mycom.project.bookmark.dto.BookMarkResultDto;

@RestController
@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
@RequestMapping(value="/alerms")
public class AlermController {
	
	@Autowired
	AlermService alermService;
	
	private final int SUCCESS = 1;
	
	@PostMapping(value="/send")
	private ResponseEntity<AlermResultDto> alermSend(@RequestBody AlermDto alermDto) {
		AlermResultDto alermResultDto;

		System.out.println(alermDto);
		alermResultDto = alermService.alermSend(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/list")
	private ResponseEntity<AlermResultDto> alermList(@RequestParam("receiveUserSeq") int receiveUserSeq) {
		AlermResultDto alermResultDto;

		AlermDto alermDto = new AlermDto();
		alermDto.setReceiveUserSeq(receiveUserSeq);
		alermResultDto = alermService.alermList(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/allRead")
	private ResponseEntity<AlermResultDto> alermAllRead(@RequestBody AlermDto alermDto) {
		AlermResultDto alermResultDto;
		System.out.println(alermDto);
		alermResultDto = alermService.alermAllRead(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete")
	private ResponseEntity<AlermResultDto> alermDelete(@RequestParam("alermId") int alermId) {
		AlermResultDto alermResultDto;

		AlermDto alermDto = new AlermDto();
		alermDto.setAlermId(alermId);
		alermResultDto = alermService.alermDelete(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete2")
	private ResponseEntity<AlermResultDto> alermDelete(@RequestParam("sendUserSeq") int sendUserSeq, @RequestParam("receiveUserSeq") int receiveUserSeq) {
		AlermResultDto alermResultDto;

		AlermDto alermDto = new AlermDto();
		alermDto.setSendUserSeq(sendUserSeq);
		alermDto.setReceiveUserSeq(receiveUserSeq);
		alermResultDto = alermService.alermDelete(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/deleteAll")
	private ResponseEntity<AlermResultDto> alermDeleteAll(@RequestParam("receiveUserSeq") int receiveUserSeq) {
		AlermResultDto alermResultDto;

		AlermDto alermDto = new AlermDto();
		alermDto.setReceiveUserSeq(receiveUserSeq);
		alermResultDto = alermService.alermDeleteAll(alermDto);
		
		if (alermResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AlermResultDto>(alermResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
