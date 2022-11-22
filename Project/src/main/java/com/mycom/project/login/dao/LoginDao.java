package com.mycom.project.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.user.dto.UserDto;

@Mapper
public interface LoginDao {
	public UserDto login(String userEmail);
	public int saveRefreshToken(UserDto userDto);
	public String getRefreshToken(int userSeq);
	public int deleteRefreshToken(int userSeq);
}
