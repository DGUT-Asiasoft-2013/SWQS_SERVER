package com.swqs.schooltrade.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.swqs.schooltrade.util.DateRecord;

@Entity
public class UserLike extends DateRecord {
	User assessee;
	User evaluator;
	Boolean isLike;

	@ManyToOne(optional = false)
	public User getAssessee() {
		return assessee;
	}

	public void setAssessee(User assessee) {
		this.assessee = assessee;
	}

	@ManyToOne(optional = false)
	public User getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(User evaluator) {
		this.evaluator = evaluator;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

}
