package com.in28minutes.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String number;

	@Column(name = "issued_country")
	private String issuedCountry;

	public Passport() {
		super();
	}

	public Passport(long id, String number, String issuedCountry) {
		super();
		this.id = id;
		this.number = number;
		this.issuedCountry = issuedCountry;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIssuedCountry() {
		return issuedCountry;
	}

	public void setIssuedCountry(String issuedCountry) {
		this.issuedCountry = issuedCountry;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + ", issuedCountry=" + issuedCountry + "] ";
	}
}

