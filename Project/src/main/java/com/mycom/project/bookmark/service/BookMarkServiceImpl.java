package com.mycom.project.bookmark.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.apt.dao.AptDao;
import com.mycom.project.apt.dto.AptDto;
import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.SGDDto;
import com.mycom.project.bookmark.dao.BookMarkDao;
import com.mycom.project.bookmark.dto.BookMarkDto;
import com.mycom.project.bookmark.dto.BookMarkResultDto;

@Service
public class BookMarkServiceImpl implements BookMarkService{

	@Autowired
	BookMarkDao bookMarkDao;
	
	@Autowired
	AptDao aptDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	@Override
	public BookMarkResultDto bookMarkList(BookMarkDto bookMarkDto) {
		BookMarkResultDto bookMarkResultDto = new BookMarkResultDto();
		try {
			List<BookMarkDto> bookMarkList = bookMarkDao.bookMarkList(bookMarkDto);
			AptParamDto aptParam = new AptParamDto();
			List<AptDto> list = new ArrayList<>();
			for (BookMarkDto dto : bookMarkList) {
				aptParam.setNo(dto.getDealNo());
				AptDto apt = aptDao.aptDetail(aptParam);
				String code = apt.getCode();
				String dong = apt.getDong();
				SGDDto dongDto = aptDao.addressDetail(code, dong);
				
				apt.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + apt.getJibun());
				list.add(apt);
			}
			bookMarkResultDto.setList(list);
			bookMarkResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			bookMarkResultDto.setResult(FAIL);
		}
		return bookMarkResultDto;
	}
	@Override
	public BookMarkResultDto bookMarkInsert(BookMarkDto bookMarkDto) {
		BookMarkResultDto bookMarkResultDto = new BookMarkResultDto();
		try {
			if(bookMarkDao.bookMarkInsert(bookMarkDto) == 1) {
				bookMarkResultDto.setResult(SUCCESS);
			}else {
				bookMarkResultDto.setResult(FAIL);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			bookMarkResultDto.setResult(FAIL);
		}
		return bookMarkResultDto;
	}
	@Override
	public BookMarkResultDto bookMarkDelete(BookMarkDto bookMarkDto) {
		BookMarkResultDto bookMarkResultDto = new BookMarkResultDto();
		try {
			if(bookMarkDao.bookMarkDelete(bookMarkDto) == 1) {
				bookMarkResultDto.setResult(SUCCESS);
			}else {
				bookMarkResultDto.setResult(FAIL);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			bookMarkResultDto.setResult(FAIL);
		}
		return bookMarkResultDto;
	}
	
	
}
