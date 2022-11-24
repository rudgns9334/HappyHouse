package com.mycom.project.user.dto;

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
public class UserParamDto {
	private int limit;
	private int offset;
	private String searchWord;

	private int userSeq;
	private String userState;
}
