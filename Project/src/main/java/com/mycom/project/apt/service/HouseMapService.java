package com.mycom.project.apt.service;

import java.util.List;

import com.mycom.project.apt.dto.HouseInfoDto;
import com.mycom.project.apt.dto.SidoGugunCodeDto;


public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
}
