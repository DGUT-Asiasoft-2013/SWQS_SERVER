package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.GoodsLike;

public interface IGoodsLikeService {

	GoodsLike like(GoodsLike like);

	GoodsLike disLike(GoodsLike like);

	int countLike(int goods_id);

	int countDisLike(int goods_id);

	GoodsLike getGoodsLike(int buyerId, int goodsId);


}
