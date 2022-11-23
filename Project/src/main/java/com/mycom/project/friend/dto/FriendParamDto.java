package com.mycom.project.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendParamDto {
	private int friendId;
	
	private int seq;
	
	private int sendSeq;
	private int receiveSeq;
	
	private String searchWord;

}