package com.mycom.project.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserFileDto;
import com.mycom.project.user.dto.UserParamDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	int userModify(UserDto userDto);
	int userWithdraw(int userSeq);
	UserDto userDetail(int userSeq);
	int checkUserDuplicate(UserDto userDto);
	UserDto userDetailWithEmail(String userEmail);
	
	List<UserDto> userList(UserParamDto userParamDto);
	int userListTotalCount(UserParamDto userParamDto);

	List<UserDto> userListSearchWord(UserParamDto userParamDto);
	int userListSearchWordTotalCount(UserParamDto userParamDto);

	UserDto userDetail(UserParamDto userParamDto);

	UserFileDto userDetailFile(int boardId);

	int userUpdate(UserDto dto);

	List<String> userFileUrlDeleteList(int boardId); 

	int userDelete(int boardId);
	int userFileDelete(int boardId);

    int userInsert(UserDto dto);

    int userFileInsert(UserFileDto dto);
}
