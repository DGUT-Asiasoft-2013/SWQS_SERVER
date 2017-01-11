package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.GoodsLike;

public interface IGoodsLikeService {

	GoodsLike like(GoodsLike like);

	GoodsLike disLike(GoodsLike like);

	int countLike(int uid);

	int countDisLike(int uid);

	GoodsLike getGoodsLike(int buyerId, int goodsId);


}
