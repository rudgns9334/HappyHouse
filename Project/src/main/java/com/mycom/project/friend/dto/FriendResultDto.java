package com.mycom.project.friend.dto;

import java.util.List;

import com.mycom.project.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendResultDto {
	private List<FriendDto> list;
	private UserDto friend;
	private int result;
}
