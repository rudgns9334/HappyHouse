package com.mycom.project.review.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.project.review.dto.ReviewDto;



@Mapper
public interface ReviewDao {
	int reviewRegister(ReviewDto reviewDto);
}
