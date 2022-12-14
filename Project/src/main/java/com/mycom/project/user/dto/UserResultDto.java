package com.mycom.project.user.dto;

import java.util.List;

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
public class UserResultDto {
	private int result;
	private int userSeq;
	private List<UserDto> list;
	private UserDto dto;
	private int count;
	
}
