package com.mycom.project.apt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;
import com.mycom.project.apt.service.AptService;

@RestController
@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class AptController {

	@Autowired
	AptService aptService;
	
	private final int SUCCESS = 1;
	
	@GetMapping(value="/apts")
	private ResponseEntity<AptResultDto> aptList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto;
		System.out.println(aptParamDto);
		if(aptParamDto.getCode() == null || aptParamDto.getCode().isEmpty()) {
			if (aptParamDto.getSearchWord() == null || aptParamDto.getSearchWord().isEmpty()) {
				aptResultDto = aptService.aptList(aptParamDto);
			} else{
				aptResultDto = aptService.aptListSearchWord(aptParamDto);
			}
		}else {
			if (aptParamDto.getSearchWord() == null || aptParamDto.getSearchWord().isEmpty()) {
				aptResultDto = aptService.aptListWithDong(aptParamDto);
			} else{
				aptResultDto = aptService.aptListWithDongSearchWord(aptParamDto);
			}
		}
		
		if (aptResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/apts/detail/{aptNo}")
	private ResponseEntity<AptResultDto> aptDetail(@PathVariable int aptNo) {
		AptResultDto aptResultDto;
		AptParamDto aptParamDto = new AptParamDto();
		aptParamDto.setNo(aptNo);
		aptResultDto = aptService.aptDetail(aptParamDto);
		
		if (aptResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/apts/sido")
	private ResponseEntity<AptResultDto> sidoList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto;
		aptResultDto = aptService.sidoList();
			
		if (aptResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/apts/gugun")
	private ResponseEntity<AptResultDto> gugunList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto;
		aptResultDto = aptService.gugunList(aptParamDto);	
		
		if (aptResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/apts/dong")
	private ResponseEntity<AptResultDto> dongList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto;
		aptResultDto = aptService.dongList(aptParamDto);
		
		if (aptResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AptResultDto>(aptResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
