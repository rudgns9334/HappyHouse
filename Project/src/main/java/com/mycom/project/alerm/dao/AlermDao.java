package com.mycom.project.alerm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.alerm.dto.AlermDto;

@Mapper
public interface AlermDao {
	int alermSend(AlermDto alermDto);
	
	List<AlermDto> alermList(AlermDto alermDto);
	
	int alermCount(AlermDto alermDto);
	
	int alermAllRead(AlermDto alermDto);
	
	int alermDelete(AlermDto alermDto);
	
	int alermDeleteAll(AlermDto alermDto);
}
