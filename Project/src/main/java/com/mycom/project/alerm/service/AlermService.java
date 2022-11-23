package com.mycom.project.alerm.service;

import com.mycom.project.alerm.dto.AlermDto;
import com.mycom.project.alerm.dto.AlermResultDto;

public interface AlermService {
	AlermResultDto alermSend(AlermDto alermDto);
	
	AlermResultDto alermList(AlermDto alermDto);
	
	AlermResultDto alermAllRead(AlermDto alermDto);
	
	AlermResultDto alermDelete(AlermDto alermDto);
	
	AlermResultDto alermDeleteAll(AlermDto alermDto);
}
