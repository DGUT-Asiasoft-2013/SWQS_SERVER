package com.swqs.schooltrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.GoodsLike;
import com.swqs.schooltrade.repository.IGoodsLikeRepository;

@Component
@Service
@Transactional
public class DefalutGoodsLikeService implements IGoodsLikeService{

	@Autowired
	IGoodsLikeRepository goodsLikeRepo;
	@Override
	public GoodsLike like(GoodsLike like) {
		// TODO Auto-generated method stub
		return goodsLikeRepo.save(like);
	}
	@Override
	public GoodsLike disLike(GoodsLike like) {
		// TODO Auto-generated method stub
		return goodsLikeRepo.save(like);
	}
	@Override
	public int countLike(int goods_id) {
		// TODO Auto-generated method stub
		return goodsLikeRepo.countLike(goods_id);
	}
	@Override
	public int countDisLike(int goods_id) {
		// TODO Auto-generated method stub
		return goodsLikeRepo.countDisLike(goods_id);
	}
	@Override
	public GoodsLike getGoodsLike(int buyerId, int goodsId) {
		// TODO Auto-generated method stub
		return goodsLikeRepo.getGoodsLike(buyerId,goodsId);
	}

}
