package com.mycom.project.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.project.review.dao.ReviewDao;
import com.mycom.project.review.dto.ReviewDto;
import com.mycom.project.review.dto.ReviewResultDto;



@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDao reviewDao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;

	@Override
	public ReviewResultDto reviewRegister(ReviewDto reviewDto) {
		System.out.println(reviewDto);
		ReviewResultDto reviewResultDto = new ReviewResultDto();
		if (reviewDao.reviewRegister(reviewDto) == 1) {
			reviewResultDto.setResult(SUCCESS);
		} else {
			reviewResultDto.setResult(FAIL);
		}
		return reviewResultDto;
	}

	

}
