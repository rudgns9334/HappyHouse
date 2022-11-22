package com.mycom.project.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.login.dao.LoginDao;
import com.mycom.project.user.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public UserDto login(UserDto dto) {
		UserDto userDto = loginDao.login(dto.getUserEmail());

		if (userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
			return userDto;
		}
		return null;
	}

	@Override
	public int saveRefreshToken(UserDto userDto) {
		
		return loginDao.saveRefreshToken(userDto);
	}

	@Override
	public String getRefreshToken(int userSeq) {
		
		return loginDao.getRefreshToken(userSeq);
	}

	@Override
	public int deleteRefreshToken(int userSeq) {
		
		return loginDao.deleteRefreshToken(userSeq);
	}



}
