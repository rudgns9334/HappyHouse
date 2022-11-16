package com.mycom.project.event.dto;

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
public class EventParamDto {

	private int limit;
	private int offset;
	private String searchWord;
	
	private int eventId;
	private int userSeq;
}
