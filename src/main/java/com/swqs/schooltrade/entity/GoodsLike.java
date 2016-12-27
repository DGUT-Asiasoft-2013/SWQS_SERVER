package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class GoodsLike extends DateRecord {

	Goods goods;
	User account;
	Boolean isLike;

	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@ManyToOne(optional = false)
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

}
