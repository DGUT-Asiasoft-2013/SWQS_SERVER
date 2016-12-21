package com.swqs.schooltrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Comment extends DateRecord {
	Goods goods;
	User account;
	Comment parentComment;
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

	@ManyToOne(optional = true)
	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
