package com.mycom.project.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SGDDto {
	private String dongCode;
	private String dongName;
	private String sidoCode;
	private String sidoName;
	private String gugunCode;
	private String gugunName;
}
