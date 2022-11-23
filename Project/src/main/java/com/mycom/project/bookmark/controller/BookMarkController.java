package com.mycom.project.bookmark.controller;

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

import com.mycom.project.bookmark.dto.BookMarkDto;
import com.mycom.project.bookmark.dto.BookMarkResultDto;
import com.mycom.project.bookmark.service.BookMarkService;

@RestController
@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
@RequestMapping(value="/bookmarks")
public class BookMarkController {
	
	@Autowired
	BookMarkService bookMarkService;
	
	private final int SUCCESS = 1;
	
	@GetMapping(value="/list/{userSeq}")
	private ResponseEntity<BookMarkResultDto> bookMarkList(@PathVariable int userSeq) {
		BookMarkResultDto bookMarkResultDto;
		
		BookMarkDto bookMarkDto = new BookMarkDto();
		bookMarkDto.setUserSeq(userSeq);
		bookMarkResultDto = bookMarkService.bookMarkList(bookMarkDto);
		
		if (bookMarkResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/insert")
	private ResponseEntity<BookMarkResultDto> bookMarkInsert(@RequestBody BookMarkDto bookMarkDto) {
		BookMarkResultDto bookMarkResultDto;
		
		bookMarkResultDto = bookMarkService.bookMarkInsert(bookMarkDto);
		
		if (bookMarkResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete/{bookmarkId}")
	private ResponseEntity<BookMarkResultDto> bookMarkDelete(@PathVariable int bookmarkId) {
		BookMarkResultDto bookMarkResultDto;
		
		BookMarkDto bookMarkDto = new BookMarkDto();
		bookMarkDto.setBookmarkId(bookmarkId);
		bookMarkResultDto = bookMarkService.bookMarkDelete(bookMarkDto);
		
		if (bookMarkResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookMarkResultDto>(bookMarkResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
