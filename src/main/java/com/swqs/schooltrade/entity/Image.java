package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.BaseEntity;

@Entity
public class Image extends BaseEntity {

	Goods goodsId;
	String pictureUrl;

	@ManyToOne(optional = false)
	public Goods getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Goods goodsId) {
		this.goodsId = goodsId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
