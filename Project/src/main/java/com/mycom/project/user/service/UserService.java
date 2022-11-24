package com.mycom.project.user.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserParamDto;
import com.mycom.project.user.dto.UserResultDto;

public interface UserService {
	UserResultDto userRegister(UserDto userDto);
	UserResultDto userModify(UserDto userDto);
	UserResultDto userWithdraw(int userSeq);
	
	UserResultDto userList(UserParamDto userParamDto);
	UserResultDto userListSearchWord(UserParamDto userParamDto);
	
	UserResultDto userDetail(UserParamDto userParamDto);

	UserResultDto userUpdate(UserDto dto, MultipartHttpServletRequest request);

	UserResultDto userDelete(int userSeq);

	UserResultDto userInsert(UserDto dto, MultipartHttpServletRequest request);
}
