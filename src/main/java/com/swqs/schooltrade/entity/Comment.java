package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Comment extends DateRecord {
	Goods goodsId;
	User accountId;
	Comment commentId;
	String text;

	@ManyToOne(optional = false)
	public Goods getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Goods goodsId) {
		this.goodsId = goodsId;
	}

	public User getAccountId() {
		return accountId;
	}

	public void setAccountId(User accountId) {
		this.accountId = accountId;
	}

	public Comment getCommentId() {
		return commentId;
	}

	public void setCommentId(Comment commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
