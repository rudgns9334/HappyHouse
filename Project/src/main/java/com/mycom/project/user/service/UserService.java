package com.mycom.project.user.service;

import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserResultDto;

public interface UserService {
	UserResultDto userRegister(UserDto userDto);
	UserResultDto userModify(UserDto userDto);
	UserResultDto userWithdraw(UserDto userDto);
}
