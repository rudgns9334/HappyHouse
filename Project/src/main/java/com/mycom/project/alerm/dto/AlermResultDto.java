package com.mycom.project.alerm.dto;

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
public class AlermResultDto {
	private int result;
	private AlermDto dto;
	private List<AlermDto> list;
	private int count;
	private boolean isNew;
}
