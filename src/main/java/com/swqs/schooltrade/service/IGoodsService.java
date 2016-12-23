package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.swqs.schooltrade.entity.Goods;

public interface IGoodsService {

	Goods save(Goods goods);

	List<Goods> getListGoods();

	Page<Goods> searchGoodsWithKeyword(String keyword, int page);

	Goods findOne(int goods_id);

	Goods getMySellGoodslist(Integer id);

	Goods getMyBuyGoodslist(Integer id);



}
