package com.mycom.project.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.event.dto.EventDto;
import com.mycom.project.event.dto.EventParamDto;

@Mapper
public interface EventDao {

	List<EventDto> eventList(EventParamDto eventParamDto);
	int eventListTotalCount(EventParamDto eventParamDto);

}
