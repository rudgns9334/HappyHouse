package com.mycom.project.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.user.dto.UserDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	int userModify(UserDto userDto);
	int userWithdraw(UserDto userDto);
	int checkUserDuplicate(UserDto userDto);
}
