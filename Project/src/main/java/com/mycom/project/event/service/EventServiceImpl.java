package com.mycom.project.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.event.dao.EventDao;
import com.mycom.project.event.dto.EventDto;
import com.mycom.project.event.dto.EventParamDto;
import com.mycom.project.event.dto.EventResultDto;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventDao eventDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public EventResultDto eventList(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		try {
			List<EventDto> list = eventDao.eventList(eventParamDto);
			int count = eventDao.eventListTotalCount(eventParamDto);
			eventResultDto.setList(list);
			eventResultDto.setCount(count);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}
}
