package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.UserLike;

public interface IUserLikeService {

	UserLike like(UserLike like);

	UserLike disLike(UserLike like);

	int countLike(int user_id);

	int countDisLike(int user_id);

}
