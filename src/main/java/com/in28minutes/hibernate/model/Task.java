package com.in28minutes.hibernate.model;

import java.util.Date;

public class Task {
	private int id;
	private int projectId;
	private Date startDate;
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", projectId=" + projectId + ", startDate=" + startDate + ", desc=" + desc + "]";
	}

}

