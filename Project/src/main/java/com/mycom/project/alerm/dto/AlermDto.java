package com.mycom.project.alerm.dto;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlermDto {
	private int alermId;
	private int sendUserSeq;
	private int receiveUserSeq;
	private String userName;
	private String contentType;
	private LocalDateTime regDt;
	private int isRead;
	
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
}

