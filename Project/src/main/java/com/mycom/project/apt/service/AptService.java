package com.mycom.project.apt.service;

import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;

public interface AptService {
	AptResultDto aptList(AptParamDto aptParamDto);
	AptResultDto aptListSearchWord(AptParamDto aptParamDto);

	AptResultDto aptDetail(AptParamDto aptParamDto);
}
