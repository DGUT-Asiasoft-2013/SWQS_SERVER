package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Judgement;
import com.swqs.schooltrade.repository.IJudgementRepository;

@Component
@Service
@Transactional
public class DefalutJudgementService implements IJudgementService {

	@Autowired
	IJudgementRepository judgementRepo;

	@Override
	public Judgement addJudgement(Judgement judgement) {
		// TODO Auto-generated method stub
		return judgementRepo.save(judgement);
	}

	@Override
	public Judgement getJudgementByGoodsId(int goods_id) {
		// TODO Auto-generated method stub
		return judgementRepo.getJudgementByGoodsId(goods_id);
	}

}
