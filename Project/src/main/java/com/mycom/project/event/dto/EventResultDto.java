package com.mycom.project.event.dto;

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
public class EventResultDto {
	private int result;
	private EventDto dto;
	private List<EventDto> list;
	private int count;
}
