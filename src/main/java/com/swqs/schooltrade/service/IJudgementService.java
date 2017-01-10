package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.Judgement;

public interface IJudgementService {

	Judgement addJudgement(Judgement judgement);

	Judgement getJudgementByGoodsId(int goods_id);

}
