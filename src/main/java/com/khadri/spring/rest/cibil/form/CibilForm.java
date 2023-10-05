package com.khadri.spring.rest.cibil.form;

import javax.validation.constraints.NotEmpty;

public class CibilForm {
	@NotEmpty(message = "Aadhar Number is Empty")
	private String adhaarNumber;
	@NotEmpty(message = "PAN Number is Empty")
	private String panNumber;

	public String getAdhaarNumber() {
		return adhaarNumber;
	}

	public void setAdhaarNumber(String adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

}
