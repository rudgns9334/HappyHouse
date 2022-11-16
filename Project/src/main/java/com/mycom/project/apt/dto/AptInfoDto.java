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
public class AptInfoDto {
	private int aptNo; 
	private String aptName;
	private String code;
	private String dealAmount;
	private String buildYear;
	private String area;
	private String floor;
	private String type;
	private String lat;
	private String lng;
	private String houseAddress;
	private String houseDealDate;
	private int houseNo;
}
