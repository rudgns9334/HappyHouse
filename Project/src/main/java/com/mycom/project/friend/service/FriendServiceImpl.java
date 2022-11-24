package com.mycom.project.friend.service;

import java.util.List;
import java.util.StringTokenizer;

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
	private final int NOSEARCHWORD = -2;
	private final int DUPLICATE = -3;
	
	@Override
	public FriendResultDto friendListSearchWord(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			String searchWord = friendParamDto.getSearchWord();
			if(searchWord == null || searchWord.equals("")) {
				friendResultDto.setResult(NOSEARCHWORD);
			}else {
				friendParamDto.setSearchWord(searchWord);
				List<FriendDto> list = friendDao.friendListSearchWord(friendParamDto);
				
				for (FriendDto dto : list) {
					friendParamDto.setReceiveSeq(dto.getUserSeq());
					System.out.println(friendParamDto);
					String state = friendDao.friendState(friendParamDto);
					System.out.println(state);
					if(state==null) dto.setFriendState("000");
					else dto.setFriendState(state);
				}
				friendResultDto.setList(list);
				friendResultDto.setResult(SUCCESS);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}
	
	@Override
	public FriendResultDto friendList(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			List<FriendDto> list = friendDao.friendList(friendParamDto);
			
			for (FriendDto friendDto : list) {
				System.out.println("friendList"+friendDto);
			}
			
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
	public FriendResultDto friendRequest(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			if(friendDao.checkDuplicate(friendParamDto) == 0) {
				if(friendDao.friendRequest(friendParamDto) == 1) {
					friendResultDto.setResult(SUCCESS);
				}else {
					friendResultDto.setResult(FAIL);
				}
			}else {
				friendResultDto.setResult(DUPLICATE);
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

	@Override
	public FriendResultDto friendAccept(FriendParamDto friendParamDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		try {
			if(friendDao.friendAccept(friendParamDto) == 1) {
				friendResultDto.setResult(SUCCESS);
			}
		}catch(Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	

}
