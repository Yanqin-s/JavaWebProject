package com.itms.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.itms.utils.JdbcUtils;

import java.sql.SQLException;

import org.junit.Test;

public class JdbcUtilsTest {
	@Test
	public void testJdbcUtils() {
		for (int i = 0; i < 100; i++) {
			try {
				System.out.println(JdbcUtils.getConnection());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
