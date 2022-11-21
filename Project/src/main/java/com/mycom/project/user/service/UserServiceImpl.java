package com.mycom.project.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.user.dao.UserDao;
import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	private final int DUPLICATE = -2;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		System.out.println(userDto);
		UserResultDto userResultDto = new UserResultDto();
		int check = userDao.checkUserDuplicate(userDto);
		if(check > 0) {
			userResultDto.setResult(DUPLICATE);
		}else {
			if (userDao.userRegister(userDto) == 1) {
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		}
		
		return userResultDto;
	}
	
	@Override
	public UserResultDto userModify(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if (userDao.userModify(userDto) == 1) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userWithdraw(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		if (userDao.userWithdraw(userSeq) == 1) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
}
