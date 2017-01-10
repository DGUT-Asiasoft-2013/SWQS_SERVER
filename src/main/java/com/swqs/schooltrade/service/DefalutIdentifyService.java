package com.swqs.schooltrade.service;

import java.util.List;

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
	public Identify save(Identify identify) {
		// TODO Auto-generated method stub
		return identifyRepo.save(identify);
	}

	@Override
	public List<Identify> getGoodsIdBySellerId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.getGoodsIdBySellerId(id);
	}

	@Override
	public List<Identify> getGoodsIdByBuyerId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.getGoodsIdByBuyerId(id);
	}

	@Override
	public Identify findIdentifyByGoodsId(Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.findIdentifyByGoodsId(id);
	}

	@Override
	public Identify findIdentifyById(int identifyId) {
		// TODO Auto-generated method stub
		return identifyRepo.findIdentifyById(identifyId);
	}

	@Override
	public int setTradeStateById(short tradestate, Integer id) {
		// TODO Auto-generated method stub
		return identifyRepo.setTradeStateById(tradestate,id);
	}

}
