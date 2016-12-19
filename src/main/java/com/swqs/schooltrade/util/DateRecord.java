package com.swqs.schooltrade.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class DateRecord extends BaseEntity {
	private Date createDate;
	private Date editDate;

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@PreUpdate
	void onPreUpdate() {
		editDate = new Date();
	}

	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		editDate = new Date();
	}
}