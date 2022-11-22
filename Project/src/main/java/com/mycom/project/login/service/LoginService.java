package com.mycom.project.login.service;

import com.mycom.project.user.dto.UserDto;

public interface LoginService {
	UserDto login(UserDto dto);
	public int saveRefreshToken(UserDto dto);
	public String getRefreshToken(int userSeq);
	public int deleteRefreshToken(int userSeq);
}
