package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.swqs.schooltrade.util.BaseEntity;
import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Identify extends DateRecord {

	Goods goods;
	User buyer;
	User seller;
	short tradeState;

	@OneToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@ManyToOne(optional = false)
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@ManyToOne(optional = false)
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public short getTradeState() {
		return tradeState;
	}

	public void setTradeState(short tradeState) {
		this.tradeState = tradeState;
	}

}
