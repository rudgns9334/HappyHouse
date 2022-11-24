package com.mycom.project.event.service;

import com.mycom.project.event.dto.EventParamDto;
import com.mycom.project.event.dto.EventResultDto;


public interface EventService {
	EventResultDto eventList(EventParamDto eventParamDto);
}
