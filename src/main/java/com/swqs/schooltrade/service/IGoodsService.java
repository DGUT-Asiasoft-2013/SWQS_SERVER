package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.swqs.schooltrade.entity.Goods;

public interface IGoodsService {

	Goods save(Goods goods);

	List<Goods> getListGoods();

	Page<Goods> searchGoodsWithKeyword(String keyword, int page);

	Goods findOne(int goods_id);

	List<Goods> getMySellGoodslist(Integer id);

	List<Goods> getMyBuyGoodslist(Integer id);



}
