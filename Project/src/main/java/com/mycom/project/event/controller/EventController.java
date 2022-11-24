	package com.mycom.project.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.project.event.dto.EventParamDto;
import com.mycom.project.event.dto.EventResultDto;
import com.mycom.project.event.service.EventService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class EventController {
	
	@Autowired
	EventService service;
	
	private final int SUCCESS = 1;
	
	@GetMapping(value="/events")
	private ResponseEntity<EventResultDto> eventList(EventParamDto eventParamDto) {
		EventResultDto eventResultDto;
		eventResultDto = service.eventList(eventParamDto);
		
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
