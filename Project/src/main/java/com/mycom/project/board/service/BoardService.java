package com.mycom.project.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.project.board.dto.BoardDto;
import com.mycom.project.board.dto.BoardParamDto;
import com.mycom.project.board.dto.BoardResultDto;

public interface BoardService {
	
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);

	BoardResultDto boardDetail(BoardParamDto boardParamDto);

	BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);

	BoardResultDto boardDelete(int boardId);

    BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
}
