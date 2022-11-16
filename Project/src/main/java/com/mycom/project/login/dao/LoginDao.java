package com.mycom.project.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.user.dto.UserDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
}
