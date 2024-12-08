package com.amit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlQueryUtil {
	
	@Autowired
	private SqlQueryConfig config;
	
	public String getSql(String key) {
		return config.getQuery(key);
	}

}
