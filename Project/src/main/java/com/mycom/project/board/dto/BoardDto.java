package com.mycom.project.board.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
public class BoardDto {
	private int boardId;
	private int userSeq;
	private String userName;
	private String userProfileImageUrl;
	private String title;
	private String content;
	private LocalDateTime regDt;
	private int readCount;
	private String boardCode;
	
	private boolean sameUser;
	
	private List<BoardFileDto> fileList;
	
	public BoardDto(int userSeq, String title, String content) {
		this.userSeq = userSeq;
		this.title = title;
		this.content = content;
	}

	public void setUserProfileImgageUrl(String userProfileImageUrl) {
		if (userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
			this.userProfileImageUrl = "/img/noPrifile.png";
		} else {
			this.userProfileImageUrl = userProfileImageUrl;
		}
	}

	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
	
}
