package com.mycom.project.friend.service;

import com.mycom.project.friend.dto.FriendParamDto;
import com.mycom.project.friend.dto.FriendResultDto;

public interface FriendService {
	FriendResultDto friendListSearchWord(FriendParamDto friendParamDto);
	FriendResultDto friendList(FriendParamDto friendParamDto);
	FriendResultDto friendDetail(FriendParamDto friendParamDto);
	FriendResultDto friendRequest(FriendParamDto friendParamDto);
	FriendResultDto friendDelete(FriendParamDto friendParamDto);
}
