package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Judgement extends DateRecord {
	Goods goods;
	User judgeAcc;
	String text;
	
	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@ManyToOne(optional = false)
	public User getJudgeAcc() {
		return judgeAcc;
	}
	public void setJudgeAcc(User judgeAcc) {
		this.judgeAcc = judgeAcc;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
}
