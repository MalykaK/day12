package com.capgemini.bankapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = "classpath: application.properties")
@ComponentScan(basePackages = {"com.capgemini.bankapp"})
public class AppConfig {

	@Value("${application.driverClassName}")
	private String driverClassName;
	@Value("${application.url}")
	private String dburl;
	@Value("${application.username}")
	private String username;
	@Value("${application.password}")
	private String password;
	
	@Bean

	public DataSource getDataSource()
	{
	
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
	/*	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/varuni");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;*/
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
		
	}
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
}