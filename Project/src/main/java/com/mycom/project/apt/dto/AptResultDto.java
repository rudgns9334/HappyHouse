package com.mycom.project.apt.dto;

import java.util.List;

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
public class AptResultDto {

	private int result;
	private AptDto dto;
	private List<AptDto> list;
	private List<SGDDto> sgdList;
	private int count;
	
}
