package com.swqs.schooltrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.ColumnDefault;

import com.swqs.schooltrade.util.BaseEntity;

@Entity
public class Identify extends BaseEntity {

	Goods goodsId;
	User buyerId;
	User sellerId;
	short tradeState;

	@Column(unique = true)
	public Goods getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Goods goodsId) {
		this.goodsId = goodsId;
	}

	public User getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(User buyerId) {
		this.buyerId = buyerId;
	}

	public User getSellerId() {
		return sellerId;
	}

	public void setSellerId(User sellerId) {
		this.sellerId = sellerId;
	}

	public short getTradeState() {
		return tradeState;
	}

	public void setTradeState(short tradeState) {
		this.tradeState = tradeState;
	}

}
