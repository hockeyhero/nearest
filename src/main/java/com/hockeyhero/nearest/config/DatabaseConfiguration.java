//package com.hockeyhero.nearest.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories("com.hockeyhero.nearest.repository")
//@EnableTransactionManagement
//public class DatabaseConfiguration {
//	
//    private final Environment env;
//
//	public DatabaseConfiguration(Environment env) {
//		this.env = env;
//	}
//	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(env.getProperty("spring.datasource.url"));
//		dataSource.setUsername(env.getProperty("spring.datasource.username")); 
//		dataSource.setPassword(env.getProperty("spring.datasource.password"));
//		return dataSource;
//	}	
//	
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}   
//}
