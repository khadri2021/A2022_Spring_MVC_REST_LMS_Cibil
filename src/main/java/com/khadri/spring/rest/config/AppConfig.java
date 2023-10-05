package com.khadri.spring.rest.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@ComponentScan(basePackages = { "com.khadri.spring.rest" })
public class AppConfig {

	@Bean
	public MappingJackson2HttpMessageConverter httpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
		return new Jaxb2RootElementHttpMessageConverter();
	}

	@Bean
	public RequestMappingHandlerAdapter handlerAdapter(MappingJackson2HttpMessageConverter converter1,
			Jaxb2RootElementHttpMessageConverter converter2) {
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		adapter.setMessageConverters(List.of(converter1, converter2));
		return adapter;
	}

}
