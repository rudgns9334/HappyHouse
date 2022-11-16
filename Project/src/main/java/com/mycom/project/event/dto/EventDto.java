package com.mycom.project.event.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String htmlUrl;
	private String stateCode;
	private String registerId;
	private LocalDateTime registerTime;
	
	public void setStartTime(Date startTime) {
		this.startTime = LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault());
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault());
	}
	
	public void setRegisterTime(Date registerTime) {
		this.registerTime = LocalDateTime.ofInstant(registerTime.toInstant(), ZoneId.systemDefault());
	}
}
