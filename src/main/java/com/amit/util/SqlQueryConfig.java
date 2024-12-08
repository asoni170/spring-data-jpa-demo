package com.amit.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
@PropertySource("classpath:EmployeeQuery.properties")
public class SqlQueryConfig {
	
	private final Properties sqlQueries;
	
	public SqlQueryConfig() throws IOException{
		Resource resoutrce = new ClassPathResource("EmployeeQuery.properties");
		this.sqlQueries = PropertiesLoaderUtils.loadProperties(resoutrce);
	}
	
	public String getQuery(String key) {
		return sqlQueries.getProperty(key);
	}

}
