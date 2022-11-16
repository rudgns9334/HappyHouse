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
public class DongDto {
	private String code;
	private String name;
	private String cityCode;
	private String cityName;
	private String gugunCode;
	private String gugunName;
}
