package com.swqs.schooltrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Goods extends DateRecord {

	String title;
	String content;
	User accountId;
	float originalPrice;
	float curPrice;

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(optional = false)
	public User getAccountId() {
		return accountId;
	}

	public void setAccountId(User accountId) {
		this.accountId = accountId;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(float curPrice) {
		this.curPrice = curPrice;
	}

}
