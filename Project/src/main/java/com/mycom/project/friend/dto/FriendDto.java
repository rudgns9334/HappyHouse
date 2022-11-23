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
public class FriendDto {
	private int friendId;
	private int sendUserSeq;
	private int receiveUserSeq;
	private String userName;
	private int userSeq;
	private String friendState;
}
