package com.mycom.project.review.service;

import com.mycom.project.review.dto.ReviewDto;
import com.mycom.project.review.dto.ReviewResultDto;

public interface ReviewService {
	ReviewResultDto reviewRegister(ReviewDto reviewDto);
}
