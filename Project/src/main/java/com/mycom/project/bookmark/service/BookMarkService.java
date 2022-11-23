package com.mycom.project.bookmark.service;

import com.mycom.project.bookmark.dto.BookMarkDto;
import com.mycom.project.bookmark.dto.BookMarkResultDto;

public interface BookMarkService {
	BookMarkResultDto bookMarkList(BookMarkDto bookMarkDto);
	BookMarkResultDto bookMarkInsert(BookMarkDto bookMarkDto);
	BookMarkResultDto bookMarkDelete(BookMarkDto bookMarkDto);
}
