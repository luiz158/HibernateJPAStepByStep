package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String number;

	@Column(name = "issued_country")
	private String issuedCountry;

	// Inverse Relationship
	// bi-directional OneToOne relationship
	// Column will not be created in the table
	// Try removing mappedBy = "passport" => You will see that student_id column
	// will be created in passport
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;

	public Passport() {
		super();
	}

	public Passport(String number, String issuedCountry) {
		super();
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number
				+ ", issuedCountry=" + issuedCountry
				+ ", student=" + student + "]";
	}

}

