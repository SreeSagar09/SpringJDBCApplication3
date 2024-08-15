package com.app.test;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.app.config.AppConfig;

public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		
		String sql = "insert into employee (employee_code, employee_name, age, designation) values (?, ?, ?, ?)";
		
		List<Object[]> batchArguments1 = new ArrayList<>();
		
		Object[] object1 = {"A001", "Sree Sagar", 30, "Software Engineer"};
		batchArguments1.add(object1);
		
		Object[] object2 = {"A002", "Sathish", 27, "Software Engineer"};
		batchArguments1.add(object2);
		
		int[] row1 = jdbcTemplate.batchUpdate(sql, batchArguments1);
		for(int i=0; i<row1.length; i++) {
			System.out.println("Record inserted successfully.");
		}
		
		List<Object[]> batchArguments2 = new ArrayList<>();
		
		Object[] object3 = {"A003", "Pavan", 26, "QA Engineer"};
		batchArguments2.add(object3);
		
		Object[] object4 = {"A002", "Aravind", 28, "QA Engineer"};
		batchArguments2.add(object4);
		int[] argumentsTypes = {Types.CHAR, Types.CHAR, Types.INTEGER, Types.CHAR};
		
		int[] row2 = jdbcTemplate.batchUpdate(sql, batchArguments2, argumentsTypes);
		for(int i=0; i<row1.length; i++) {
			System.out.println("Record inserted successfully.");
		}
	}
}
