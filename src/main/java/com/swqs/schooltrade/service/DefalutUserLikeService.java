package com.swqs.schooltrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.UserLike;
import com.swqs.schooltrade.repository.IUserLikeRepository;

@Component
@Service
@Transactional
public class DefalutUserLikeService implements IUserLikeService {

	@Autowired
	IUserLikeRepository userLikeRepo;

	@Override
	public UserLike like(UserLike like) {
		// TODO Auto-generated method stub
		return userLikeRepo.save(like);
	}

	@Override
	public UserLike disLike(UserLike like) {
		// TODO Auto-generated method stub
		return userLikeRepo.save(like);
	}

	@Override
	public int countLike(int user_id) {
		// TODO Auto-generated method stub
		return userLikeRepo.countLike(user_id);
	}

	@Override
	public int countDisLike(int user_id) {
		// TODO Auto-generated method stub
		return userLikeRepo.countDisLike(user_id);
	}
}
