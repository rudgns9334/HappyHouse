package com.mycom.project.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AptParamDto {

	private int limit;
	private int offset;
	private String searchWord;

	private String code;
	
	private int no;
}
