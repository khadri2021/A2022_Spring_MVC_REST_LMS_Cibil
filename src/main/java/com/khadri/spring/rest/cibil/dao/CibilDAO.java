package com.khadri.spring.rest.cibil.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.khadri.spring.rest.cibil.entity.CibilEntity;

@Repository
public class CibilDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String checkCibil(CibilEntity ce) {

		String sql = "select CIBIL_SCORE from cibil_data  where ADHAAR_NUMBER ='" + ce.getADHAAR_NUMBER()
				+ "' and PAN_NUMBER ='" + ce.getPAN_NUMBER() + "'";
		String sql1 = "select ADHAAR_NUMBER,PAN_NUMBER from account_data where ADHAAR_NUMBER ='" + ce.getADHAAR_NUMBER()
				+ "' and PAN_NUMBER ='" + ce.getPAN_NUMBER() + "'";

		try {

			CibilEntity cibildb = jdbcTemplate.queryForObject(sql, rowMapperForCibilScore());
			CibilEntity accountdb = jdbcTemplate.queryForObject(sql1, rowMapperForExistingAccountUserOrNot());

			String adhaar_number = accountdb.getADHAAR_NUMBER();
			String pan_number = accountdb.getPAN_NUMBER();

			String cibil = cibildb.getCIBIL_SCORE();
			if (Optional.ofNullable(cibil).isPresent() && Optional.ofNullable(adhaar_number).isPresent()
					&& Optional.ofNullable(pan_number).isPresent()) {

				if (Integer.parseInt(cibil) >= 60) {
					String sql2 = "update account_data set LOAN_ELIGIBLE ='Eligible' where ADHAAR_NUMBER = ?";
					jdbcTemplate.update(sql2, ce.getADHAAR_NUMBER());
					return "CONGRATULATIONS! YOU ARE ELIGIBLE FOR LOAN.";

				} else {
					String sql3 = "update account_data set LOAN_ELIGIBLE ='Ineligible' where ADHAAR_NUMBER = ?";
					jdbcTemplate.update(sql3, ce.getADHAAR_NUMBER());
					return "YOUR NOT ELIGIBLE FOR LOAN";
				}
			}
		} catch (EmptyResultDataAccessException erdae) {
			return "INVALID DETAILS";
		}
		return "NOT	FOUND CIBIL SCORE";
	}

	private RowMapper<CibilEntity> rowMapperForCibilScore() {

		Optional<RowMapper<CibilEntity>> optional = Optional.ofNullable((resultSet, rowNum) -> {
			CibilEntity entity = new CibilEntity();
			entity.setCIBIL_SCORE(resultSet.getString("CIBIL_SCORE"));
			return entity;
		});

		return optional.isEmpty() ? null : optional.get();
	}

	private RowMapper<CibilEntity> rowMapperForExistingAccountUserOrNot() {

		Optional<RowMapper<CibilEntity>> optional = Optional.ofNullable((resultSet, rowNum) -> {
			CibilEntity entity = new CibilEntity();
			entity.setADHAAR_NUMBER(resultSet.getString("ADHAAR_NUMBER"));
			entity.setPAN_NUMBER(resultSet.getString("PAN_NUMBER"));
			return entity;
		});

		return optional.isEmpty() ? null : optional.get();
	}

}
