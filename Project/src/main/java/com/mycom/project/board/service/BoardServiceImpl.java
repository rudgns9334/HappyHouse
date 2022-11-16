package com.mycom.project.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.project.board.dao.BoardDao;
import com.mycom.project.board.dto.BoardDto;
import com.mycom.project.board.dto.BoardFileDto;
import com.mycom.project.board.dto.BoardParamDto;
import com.mycom.project.board.dto.BoardResultDto;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;

	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardDto> list = boardDao.boardList(boardParamDto);
			int count = boardDao.boardListTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = boardDao.boardListSearchWord(boardParamDto);
			int count = boardDao.boardListSearchWordTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			int userReadCount = boardDao.boardUserReadCount(boardParamDto);
			if (userReadCount == 0) {
				boardDao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
				boardDao.boardReadCountUpdate(boardParamDto.getBoardId());
			}
			BoardDto boardDto = boardDao.boardDetail(boardParamDto);
			if (boardDto.getUserSeq() == boardParamDto.getUserSeq()) {
				boardDto.setSameUser(true);
			} else {
				boardDto.setSameUser(false);
			}

			List<BoardFileDto> fileList = boardDao.boardDetailFileList(boardDto.getBoardId());
			boardDto.setFileList(fileList);

			boardResultDto.setDto(boardDto);
			
			boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			boardDao.boardUpdate(dto);
			
			File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder);
		    if (!uploadDir.exists()) uploadDir.mkdir();

		    List<String> fileUrlList = boardDao.boardFileUrlDeleteList(dto.getBoardId());
		    
		    for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}

		    boardDao.boardFileDelete(dto.getBoardId());

			List<MultipartFile> fileList = request.getFiles("file");
	        
	        for (MultipartFile partFile : fileList) {
				int boardId = dto.getBoardId();
				String fileName = partFile.getOriginalFilename();
	
				UUID uuid = UUID.randomUUID();

				String extension = FilenameUtils.getExtension(fileName);

				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);

				partFile.transferTo(destFile);

				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				boardDao.boardFileInsert(boardFileDto);
				
	        }
	        boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<String> fileUrlList = boardDao.boardFileUrlDeleteList(boardId);
		    
		    for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}

		    boardDao.boardReadCountDelete(boardId);
		    boardDao.boardFileDelete(boardId);
			boardDao.boardDelete(boardId);
			
			boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}
	
	@Override
	@Transactional
	public BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
	    BoardResultDto boardResultDto = new BoardResultDto();

	    try {
	        boardDao.boardInsert(boardDto);
	        System.out.println("gemerated key : " + boardDto.getBoardId());
	        
	        File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder);
	        if (!uploadDir.exists()) uploadDir.mkdir();
	        
	        List<MultipartFile> fileList = request.getFiles("file");

	        for (MultipartFile partFile : fileList) {
				int boardId = boardDto.getBoardId();
				String fileName = partFile.getOriginalFilename();

				UUID uuid = UUID.randomUUID();

				String extension = FilenameUtils.getExtension(fileName);

				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);

				partFile.transferTo(destFile);

				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				boardDao.boardFileInsert(boardFileDto);
			
	        }
	        boardResultDto.setResult(SUCCESS);

	    }catch(IOException e) {
	        e.printStackTrace();
	        boardResultDto.setResult(FAIL);
	    }
	    
	    return boardResultDto;
	}
}
