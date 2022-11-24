package com.mycom.project.user.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.project.user.dao.UserDao;
import com.mycom.project.user.dto.UserDto;
import com.mycom.project.user.dto.UserFileDto;
import com.mycom.project.user.dto.UserParamDto;
import com.mycom.project.user.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	private final int DUPLICATE = -2;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		System.out.println(userDto);
		UserResultDto userResultDto = new UserResultDto();
		int check = userDao.checkUserDuplicate(userDto);
		if(check > 0) {
			userResultDto.setResult(DUPLICATE);
		}else {
			userDto.setUserProfileImageUrl(userDto.getUserProfileImageUrl());
			System.out.println(userDto);
			if (userDao.userRegister(userDto) == 1) {
				UserDto dto = userDao.userDetailWithEmail(userDto.getUserEmail());
				userResultDto.setUserSeq(dto.getUserSeq());
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		}
		
		return userResultDto;
	}
	
	@Override
	public UserResultDto userModify(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if (userDao.userModify(userDto) == 1) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userWithdraw(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		if (userDao.userWithdraw(userSeq) == 1) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userList(UserParamDto userParamDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			List<UserDto> list = userDao.userList(userParamDto);
			int count = userDao.userListTotalCount(userParamDto);
			userResultDto.setList(list);
			userResultDto.setCount(count);
			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userListSearchWord(UserParamDto userParamDto) {
		
		UserResultDto userResultDto = new UserResultDto();
		try {
			List<UserDto> list = userDao.userListSearchWord(userParamDto);
			int count = userDao.userListSearchWordTotalCount(userParamDto);
			userResultDto.setList(list);
			userResultDto.setCount(count);
			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDetail(UserParamDto userParamDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			
			UserDto userDto = userDao.userDetail(userParamDto);

//			UserFileDto file = userDao.userDetailFile(userDto.getUserSeq());
//			userDto.setUserProfileImageUrl(file.getFileName());

			userResultDto.setDto(userDto);
			
			userResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userUpdate(UserDto dto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			userDao.userUpdate(dto);
			
			File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder);
		    if (!uploadDir.exists()) uploadDir.mkdir();

		    List<String> fileUrlList = userDao.userFileUrlDeleteList(dto.getUserSeq());
		    
		    for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}

		    userDao.userFileDelete(dto.getUserSeq());

			List<MultipartFile> fileList = request.getFiles("file");
	        
	        for (MultipartFile partFile : fileList) {
				int userSeq = dto.getUserSeq();
				String fileName = partFile.getOriginalFilename();
	
				UUID uuid = UUID.randomUUID();

				String extension = FilenameUtils.getExtension(fileName);

				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);

				partFile.transferTo(destFile);

				UserFileDto userFileDto = new UserFileDto();
				userFileDto.setUserSeq(userSeq);
				userFileDto.setFileName(fileName);
				userFileDto.setFileSize(partFile.getSize());
				userFileDto.setFileContentType(partFile.getContentType());
				userFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				userDao.userFileInsert(userFileDto);
				
	        }
	        userResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			List<String> fileUrlList = userDao.userFileUrlDeleteList(userSeq);
		    
		    for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}

		    userDao.userFileDelete(userSeq);
		    userDao.userDelete(userSeq);
			
			userResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userInsert(UserDto dto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();

	    try {
	    	userDao.userInsert(dto);
	        System.out.println("gemerated key : " + dto.getUserSeq());
	        
	        File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder);
	        if (!uploadDir.exists()) uploadDir.mkdir();
	        
List<MultipartFile> fileList = request.getFiles("file");
	        
	        for (MultipartFile partFile : fileList) {
				int userSeq = dto.getUserSeq();
				String fileName = partFile.getOriginalFilename();
	
				UUID uuid = UUID.randomUUID();

				String extension = FilenameUtils.getExtension(fileName);

				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);

				partFile.transferTo(destFile);

				UserFileDto userFileDto = new UserFileDto();
				userFileDto.setUserSeq(userSeq);
				userFileDto.setFileName(fileName);
				userFileDto.setFileSize(partFile.getSize());
				userFileDto.setFileContentType(partFile.getContentType());
				userFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				userDao.userFileInsert(userFileDto);
				
	        }
	        userResultDto.setResult(SUCCESS);

	    }catch(IOException e) {
	        e.printStackTrace();
	        userResultDto.setResult(FAIL);
	    }
	    
	    return userResultDto;
	}
}
