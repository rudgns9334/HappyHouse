package com.mycom.project.apt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.project.apt.dto.AptDto;
import com.mycom.project.apt.dto.AptInfoDto;
import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.DongDto;

@Mapper
public interface AptDao {
	List<AptDto> aptList(AptParamDto aptParamDto);
	int aptListTotalCount();

	List<AptDto> aptListSearchWord(AptParamDto aptParamDto);
	int aptListSearchWordTotalCount(AptParamDto aptParamDto);

	AptDto aptDetail(AptParamDto aptParamDto);
	DongDto addressDetail(@Param("code") String code, @Param("name") String dong);
	AptInfoDto aptLatLng(int houseNo);
}
