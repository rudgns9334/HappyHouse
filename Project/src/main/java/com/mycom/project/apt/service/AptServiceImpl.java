package com.mycom.project.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.apt.dao.AptDao;
import com.mycom.project.apt.dto.AptDto;
import com.mycom.project.apt.dto.AptParamDto;
import com.mycom.project.apt.dto.AptResultDto;
import com.mycom.project.apt.dto.SGDDto;

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
			for (AptDto aptDto : list) {
				String code = aptDto.getCode();
				String dong = aptDto.getDong();
				SGDDto dongDto = dao.addressDetail(code, dong);
				
				aptDto.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + aptDto.getJibun());
			}
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
			for (AptDto aptDto : list) {
				String code = aptDto.getCode();
				String dong = aptDto.getDong();
				SGDDto dongDto = dao.addressDetail(code, dong);
				
				aptDto.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + aptDto.getJibun());
			}
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
	public AptResultDto aptListWithDong(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<AptDto> list = dao.aptListWithDong(aptParamDto);
			int count = dao.aptListWithDongTotalCount(aptParamDto);
			for (AptDto aptDto : list) {
				String code = aptDto.getCode();
				String dong = aptDto.getDong();
				SGDDto dongDto = dao.addressDetail(code, dong);
				
				aptDto.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + aptDto.getJibun());
			}
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
	public AptResultDto aptListWithDongSearchWord(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<AptDto> list = dao.aptListWithDongSearchWord(aptParamDto);
			int count = dao.aptListWithDongSearchWordTotalCount(aptParamDto);
			for (AptDto aptDto : list) {
				String code = aptDto.getCode();
				String dong = aptDto.getDong();
				SGDDto dongDto = dao.addressDetail(code, dong);
				
				aptDto.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + aptDto.getJibun());
			}
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
			
			String code = aptDto.getCode();
			String dong = aptDto.getDong();
			SGDDto dongDto = dao.addressDetail(code, dong);
			
			aptDto.setHouseAddress(dongDto.getSidoName() + " " + dongDto.getGugunName() + " " + dongDto.getDongName() + " " + aptDto.getJibun());
			
			aptResultDto.setDto(aptDto);
			
			aptResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	@Override
	public AptResultDto sidoList() {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<SGDDto> sgdList = dao.sidoList();
			aptResultDto.setSgdList(sgdList);
			aptResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	@Override
	public AptResultDto gugunList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<SGDDto> sgdList = dao.gugunList(aptParamDto);
			aptResultDto.setSgdList(sgdList);
			aptResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	@Override
	public AptResultDto dongList(AptParamDto aptParamDto) {
		AptResultDto aptResultDto = new AptResultDto();
		try {
			List<SGDDto> sgdList = dao.dongList(aptParamDto);
			aptResultDto.setSgdList(sgdList);
			aptResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			aptResultDto.setResult(FAIL);
		}
		return aptResultDto;
	}

	

}
