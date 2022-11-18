package com.mycom.project.apt.service;

import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;

public interface AptService {
	AptResultDto aptList(AptParamDto aptParamDto);
	AptResultDto aptListSearchWord(AptParamDto aptParamDto);
	AptResultDto aptListWithDong(AptParamDto aptParamDto);
	AptResultDto aptListWithDongSearchWord(AptParamDto aptParamDto);
	
	AptResultDto aptDetail(AptParamDto aptParamDto);
	
	AptResultDto sidoList();
	AptResultDto gugunList(AptParamDto aptParamDto);
	AptResultDto dongList(AptParamDto aptParamDto);
}
