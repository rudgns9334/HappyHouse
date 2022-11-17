package com.mycom.project.apt.service;

import java.util.List;

import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;
import com.mycom.project.apt.dto.HouseInfoDto;
import com.mycom.project.apt.dto.SidoGugunCodeDto;

public interface AptService {
	AptResultDto aptList(AptParamDto aptParamDto);
	AptResultDto aptListSearchWord(AptParamDto aptParamDto);

	AptResultDto aptDetail(AptParamDto aptParamDto);
}
