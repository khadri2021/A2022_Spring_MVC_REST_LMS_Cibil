package com.khadri.spring.rest.cibil.entity;

public class CibilEntity {

	private String ADHAAR_NUMBER;
	private String PAN_NUMBER;
	private String CIBIL_SCORE;

	public String getADHAAR_NUMBER() {
		return ADHAAR_NUMBER;
	}

	public void setADHAAR_NUMBER(String aDHAAR_NUMBER) {
		ADHAAR_NUMBER = aDHAAR_NUMBER;
	}

	public String getPAN_NUMBER() {
		return PAN_NUMBER;
	}

	public void setPAN_NUMBER(String pAN_NUMBER) {
		PAN_NUMBER = pAN_NUMBER;
	}

	public String getCIBIL_SCORE() {
		return CIBIL_SCORE;
	}

	public void setCIBIL_SCORE(String cIBIL_SCORE) {
		CIBIL_SCORE = cIBIL_SCORE;
	}

}
