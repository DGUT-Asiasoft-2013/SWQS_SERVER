package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.Identify;

public interface IIdentifyService {

	Identify save(Identify identfy);

	List<Identify> getGoodsIdBySellerId(Integer id);

	List<Identify> getGoodsIdByBuyerId(Integer id);

	Identify findIdentifyByGoodsId(Integer goods_id);

}
