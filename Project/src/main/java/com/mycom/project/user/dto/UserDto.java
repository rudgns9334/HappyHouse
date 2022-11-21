package com.mycom.project.user.dto;

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
public class UserDto {
	
	private int userSeq;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImageUrl;
    private LocalDateTime userRegisterDate;
    private String userState;
    private String userEventPart;
    private String userComment;
  
	public void setUserProfileImageUrl(String userProfileImageUrl) {
		if( userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
			this.userProfileImageUrl = "/img/noProfile.png";
		}else {
			this.userProfileImageUrl = userProfileImageUrl;			
		}
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = LocalDateTime.ofInstant(userRegisterDate.toInstant(), ZoneId.systemDefault());
	}
	
}
