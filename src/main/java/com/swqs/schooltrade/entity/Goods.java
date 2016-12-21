package com.swqs.schooltrade.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class Goods extends DateRecord {

	String title;
	String content;
	User account;
	float originalPrice;
	float curPrice;
	List<Image> listImage;

	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "goods")
	public List<Image> getListImage() {
		return listImage;
	}

	public void setListImage(List<Image> listImage) {
		this.listImage = listImage;
	}

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
	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
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
