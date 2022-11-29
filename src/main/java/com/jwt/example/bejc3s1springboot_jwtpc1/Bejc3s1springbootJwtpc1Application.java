package com.jwt.example.bejc3s1springboot_jwtpc1;

import com.jwt.example.bejc3s1springboot_jwtpc1.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Bejc3s1springbootJwtpc1Application {

	public static void main(String[] args) {

		SpringApplication.run(Bejc3s1springbootJwtpc1Application.class, args);

	}
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/customerdata/v1/*");
		return filterRegistrationBean;

	}
}
