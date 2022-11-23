package com.mycom.project.bookmark.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.bookmark.dto.BookMarkDto;

@Mapper
public interface BookMarkDao {
	List<BookMarkDto> bookMarkList(BookMarkDto bookMarkDto);
	int bookMarkInsert(BookMarkDto bookMarkDto);
	int bookMarkDelete(BookMarkDto bookMarkDto);
	
}
