package com.mycom.project.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.apt.dao.AptDao;
import com.mycom.project.apt.dto.AptDto;
import com.mycom.project.apt.dto.AptInfoDto;
import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;
import com.mycom.project.apt.dto.DongDto;

@Service
public class AptServiceImpl implements AptService{

	@Autowired
	AptDao dao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public AptResultDto aptList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<AptDto> list = dao.aptList(aptParamDto);
			int count = dao.aptListTotalCount();
			aptResultDto.setList(list);
			aptResultDto.setCount(count);
			aptResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	@Override
	public AptResultDto aptListSearchWord(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<AptDto> list = dao.aptListSearchWord(aptParamDto);
			int count = dao.aptListSearchWordTotalCount(aptParamDto);
			aptResultDto.setList(list);
			aptResultDto.setCount(count);
			aptResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	@Override
	public AptResultDto aptDetail(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			
			AptDto aptDto = dao.aptDetail(aptParamDto);
			AptInfoDto infoDto = dao.aptLatLng(aptDto.getHouseNo());
			
			infoDto.setAptNo(aptDto.getNo());
			infoDto.setAptName(aptDto.getAptName());
			infoDto.setDealAmount(aptDto.getDealAmount());
			infoDto.setBuildYear(aptDto.getBuildYear());
			infoDto.setHouseDealDate(aptDto.getDealYear() + "." + aptDto.getDealMonth() + "." + aptDto.getDealDay());
			infoDto.setArea(aptDto.getArea());
			infoDto.setFloor(aptDto.getFloor());
			infoDto.setType(aptDto.getType());
			infoDto.setHouseNo(aptDto.getHouseNo());
			
			String code = aptDto.getCode();
			String dong = aptDto.getDong();
			DongDto dongDto = dao.addressDetail(code, dong);
			
			infoDto.setHouseAddress(dongDto.getCityName() + " " + dongDto.getGugunName() + " " + dongDto.getName() + " " + aptDto.getJibun());
			
			
			aptResultDto.setDto(aptDto);
			aptResultDto.setInfoDto(infoDto);
			
			aptResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

}
