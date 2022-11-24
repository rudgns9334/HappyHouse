package com.mycom.project.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.friend.dto.FriendDto;
import com.mycom.project.friend.dto.FriendParamDto;
import com.mycom.project.user.dto.UserDto;

@Mapper
public interface FriendDao {
	List<FriendDto> friendListSearchWord(FriendParamDto friendParamDto);
	String friendState(FriendParamDto friendParamDto);
	
	List<FriendDto> friendList(FriendParamDto friendParamDto);
	UserDto friendDetail(FriendParamDto friendParamDto);
	
	int friendRequest(FriendParamDto friendParamDto);
	
	int checkDuplicate(FriendParamDto friendParamDto);
	
	int friendAccept(FriendParamDto friendParamDto);
	
	int friendDelete(FriendParamDto friendParamDto);
}
