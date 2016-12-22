package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.Identify;

public interface IIdentifyService {

	Identify save(Identify identfy);

	Identify getGoodsIdBySellerId(Integer id);

	Identify getGoodsIdByBuyerId(Integer id);

	Identify findIdentifyByGoodsId(Integer goods_id);

}
