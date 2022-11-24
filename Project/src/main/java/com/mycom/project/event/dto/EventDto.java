package com.mycom.project.event.dto;

import java.time.LocalDateTime;

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
public class EventDto {
	private int eventId;
	private String eventName;
	private LocalDateTime startDt;
	private LocalDateTime endDt;
	private String imgUrl;	
}
