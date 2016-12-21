package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swqs.schooltrade.util.BaseEntity;

@Entity
public class Image extends BaseEntity {

	Goods goods;
	String pictureUrl;

	@JsonIgnore
	@ManyToOne(optional = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
