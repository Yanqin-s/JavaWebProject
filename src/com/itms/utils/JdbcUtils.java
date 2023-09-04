
package com.itms.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {
	private static DruidDataSource dataSource;
	
	static {
		Properties properties = new Properties();
		try {

			InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(inputStream);
			//创建数据库连接池
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

			System.out.println(dataSource.getConnection());
		}catch (Exception e) {
			// TODO: handle exceptions
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {

			conn = dataSource.getConnection(); // 建立连接
		} catch (Exception e) {
			System.out.println("SQL程序初始化失败");
			e.printStackTrace();
		}
		return conn;
	}



	/**
	 * 关闭连接，放回数据库连接池
	 */
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("数据库关闭异常");
				e.printStackTrace();
			}
		}
	}




}
