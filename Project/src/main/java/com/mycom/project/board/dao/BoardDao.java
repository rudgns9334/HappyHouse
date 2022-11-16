package com.mycom.project.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.project.board.dto.BoardDto;
import com.mycom.project.board.dto.BoardFileDto;
import com.mycom.project.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {

	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardListTotalCount(BoardParamDto boardParamDto);

	List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto);

	BoardDto boardDetail(BoardParamDto boardParamDto);

	List<BoardFileDto> boardDetailFileList(int boardId);

	int boardUpdate(BoardDto dto);

	List<String> boardFileUrlDeleteList(int boardId); 

	int boardDelete(int boardId);
	int boardFileDelete(int boardId);

    int boardInsert(BoardDto dto);

    int boardFileInsert(BoardFileDto dto);

    int boardUserReadCount(BoardParamDto boardParamDto);
    int boardUserReadInsert(@Param("boardId") int boardId, @Param("userSeq") int userSeq);
    
    int boardReadCountUpdate(int boardId);
    int boardReadCountDelete(int boardId);
}
