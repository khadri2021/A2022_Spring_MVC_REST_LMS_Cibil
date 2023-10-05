package com.khadri.spring.rest.config;

import org.springframework.beans.factory.annotation.Value;

public class DbProperties {
	@Value("${DRIVER_CLASS}")
	private String driverClass;

	@Value("${URL}")
	private String url;

	@Value("${USER}")
	private String userName;

	@Value("${PASSWORD}")
	private String password;

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
