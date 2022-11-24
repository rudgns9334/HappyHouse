package com.mycom.project.review.controller;

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

import com.mycom.project.review.dto.ReviewDto;
import com.mycom.project.review.dto.ReviewResultDto;
import com.mycom.project.review.service.ReviewService;


@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	private final int SUCCESS = 1;
	
	@PostMapping(value="/review")
	public ResponseEntity<ReviewResultDto> register(@RequestBody ReviewDto dto) {
		System.out.println(dto);
		ReviewResultDto reviewResultDto;
		
		reviewResultDto = reviewService.reviewRegister(dto);
		
		
		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new  ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.NOT_FOUND);
		}
	}
}
