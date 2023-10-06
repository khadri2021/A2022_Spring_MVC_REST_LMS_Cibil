package com.khadri.spring.rest.cibil.controller;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khadri.spring.rest.cibil.dto.CibilDTO;
import com.khadri.spring.rest.cibil.form.CibilForm;
import com.khadri.spring.rest.cibil.service.CibilService;

@RestController
@RequestMapping("/cibil")
public class CibilController {

	@Autowired
	private CibilService cibilService;

	@RequestMapping("/check")
	public String checkCibil(@Valid @ModelAttribute("cibilForm") CibilForm cibilForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<ObjectError> list = bindingResult.getAllErrors().stream().toList();
			ListIterator<ObjectError> listIterator = list.listIterator();
			if (listIterator.hasNext()) {
				ObjectError next = listIterator.next();
				return next.getDefaultMessage();
			}
		}

		return cibilService.checkCibil(Optional.ofNullable(cibilForm).stream().map((form) -> {

			CibilDTO dto = new CibilDTO();
			dto.setaNum(form.getAdhaarNumber());
			dto.setpNum(form.getPanNumber());
			return dto;
		}).findFirst().get());
	}

}