package com.mycom.project.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycom.project.board.dto.BoardResultDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BoardResultDto> handleErrorResponseEntity(Exception e) {
		e.printStackTrace();
	
		System.out.println("GlobalExceptionHandler!!");
		
		BoardResultDto boardResultDto = new BoardResultDto();
		boardResultDto.setResult(-1);
		return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
