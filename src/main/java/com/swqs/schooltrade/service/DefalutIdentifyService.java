package com.swqs.schooltrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Identify;
import com.swqs.schooltrade.repository.IIdentifyRepository;

@Component
@Service
@Transactional
public class DefalutIdentifyService implements IIdentifyService {

	@Autowired
	IIdentifyRepository identifyRepo;

	@Override
	public Identify save(Identify identfy) {
		// TODO Auto-generated method stub
		return identifyRepo.save(identfy);
	}

	@Override
	public Identify getGoodsIdBySellerId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.getGoodsIdBySellerId(id);
	}

	@Override
	public Identify getGoodsIdByBuyerId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.getGoodsIdByBuyerId(id);
	}

	@Override
	public Identify findIdentifyByGoodsId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.findIdentifyByGoodsId(id);
	}
}
