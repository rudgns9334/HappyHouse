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
	private AptInfoDto infoDto;
	private List<AptDto> list;
	private int count;
	
}
