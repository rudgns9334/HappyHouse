package com.mycom.project.alerm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.alerm.dao.AlermDao;
import com.mycom.project.alerm.dto.AlermDto;
import com.mycom.project.alerm.dto.AlermResultDto;

@Service
public class AlermServiceImpl implements AlermService{

	@Autowired
	AlermDao alermDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	
	@Override
	public AlermResultDto alermSend(AlermDto alermDto) {
		AlermResultDto alermResultDto = new AlermResultDto();
		try {
			if(alermDao.alermSend(alermDto) == 1) {
				alermResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			alermResultDto.setResult(FAIL);
		}
		return alermResultDto;
	}

	@Override
	public AlermResultDto alermAllRead(AlermDto alermDto) {
		AlermResultDto alermResultDto = new AlermResultDto();
		try {
			if(alermDao.alermAllRead(alermDto)>=0) {
				alermResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			alermResultDto.setResult(FAIL);
		}
		return alermResultDto;
	}
	
	@Override
	public AlermResultDto alermList(AlermDto alermDto) {
		AlermResultDto alermResultDto = new AlermResultDto();
		try {
			List<AlermDto> list = alermDao.alermList(alermDto);
			int count = alermDao.alermCount(alermDto);
			alermResultDto.setNew(false);
			for (AlermDto dto : list) {
				if(dto.getIsRead() == 0) {
					alermResultDto.setNew(true);
					break;
				}
			}
			alermResultDto.setList(list);
			alermResultDto.setCount(count);
			alermResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			alermResultDto.setResult(FAIL);
		}
		return alermResultDto;
	}



	@Override
	public AlermResultDto alermDelete(AlermDto alermDto) {
		AlermResultDto alermResultDto = new AlermResultDto();
		System.out.println("암커밍");
		try {
			if(alermDao.alermDelete(alermDto) == 1) {
				alermResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			alermResultDto.setResult(FAIL);
		}
		return alermResultDto;
	}

	@Override
	public AlermResultDto alermDeleteAll(AlermDto alermDto) {
		AlermResultDto alermResultDto = new AlermResultDto();
		try {
			if(alermDao.alermDeleteAll(alermDto) == 1) {
				alermResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			alermResultDto.setResult(FAIL);
		}
		return alermResultDto;
	}

	

}
