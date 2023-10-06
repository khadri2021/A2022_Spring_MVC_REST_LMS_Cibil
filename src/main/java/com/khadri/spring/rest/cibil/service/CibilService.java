package com.khadri.spring.rest.cibil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadri.spring.rest.cibil.dao.CibilDAO;
import com.khadri.spring.rest.cibil.dto.CibilDTO;
import com.khadri.spring.rest.cibil.entity.CibilEntity;

@Service

public class CibilService {

	@Autowired
	private CibilDAO cd;

	public String checkCibil(CibilDTO cdto) {

		return cd.checkCibil(Optional.ofNullable(cdto).stream().map((dto) -> {
			CibilEntity ce = new CibilEntity();
			ce.setADHAAR_NUMBER(dto.getaNum());
			ce.setPAN_NUMBER(dto.getpNum());
			return ce;
		}).findFirst().get());
	}

}
