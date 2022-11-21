package com.mycom.project.apt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.project.apt.dto.AptDto;
import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.SGDDto;

@Mapper
public interface AptDao {
	List<AptDto> aptList(AptParamDto aptParamDto);
	int aptListTotalCount();

	List<AptDto> aptListSearchWord(AptParamDto aptParamDto);
	int aptListSearchWordTotalCount(AptParamDto aptParamDto);

	List<AptDto> aptListWithDong(AptParamDto aptParamDto);
	int aptListWithDongTotalCount(AptParamDto aprParamDto);
	
	List<AptDto> aptListWithDongSearchWord(AptParamDto aptParamDto);
	int aptListWithDongSearchWordTotalCount(AptParamDto aprParamDto);
	
	AptDto aptDetail(AptParamDto aptParamDto);
	SGDDto addressDetail(@Param("code") String code, @Param("name") String dong);
	
	List<SGDDto> sidoList();
	List<SGDDto> gugunList(AptParamDto aptParamDto);
	List<SGDDto> dongList(AptParamDto aptParamDto);
	
}

