package com.mycom.project.bookmark.dto;

import java.util.List;

import com.mycom.project.apt.dto.AptDto;

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
public class BookMarkResultDto {
	private List<AptDto> list;
	private AptDto apt;
	private int result;
}
