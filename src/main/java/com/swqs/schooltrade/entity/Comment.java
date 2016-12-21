package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Comment extends DateRecord {
	Goods goods;
	User account;
	Comment comment;
	String text;

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

	@ManyToOne(optional = false)
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
