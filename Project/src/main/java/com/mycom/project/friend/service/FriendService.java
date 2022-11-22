package com.mycom.project.friend.service;

import com.mycom.project.friend.dto.FriendParamDto;
import com.mycom.project.friend.dto.FriendResultDto;

public interface FriendService {
	FriendResultDto friendList(FriendParamDto friendParamDto);
	FriendResultDto friendDetail(FriendParamDto friendParamDto);
	FriendResultDto friendRegister(FriendParamDto friendParamDto);
	FriendResultDto friendDelete(FriendParamDto friendParamDto);
}
