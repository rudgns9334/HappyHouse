package com.mycom.project.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.friend.dao.FriendDao;
import com.mycom.project.friend.dto.FriendDto;
import com.mycom.project.friend.dto.FriendParamDto;
import com.mycom.project.friend.dto.FriendResultDto;
import com.mycom.project.user.dto.UserDto;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDao friendDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public FriendResultDto friendList(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			List<FriendDto> list = friendDao.friendList(friendParamDto);
			friendResultDto.setList(list);
			friendResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendDetail(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			UserDto friend = friendDao.friendDetail(friendParamDto);
			friendResultDto.setFriend(friend);
			friendResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendRegister(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			if(friendDao.friendRegister(friendParamDto) == 1) {
				friendResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendDelete(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			if(friendDao.friendDelete(friendParamDto) == 1) {
				friendResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

}
