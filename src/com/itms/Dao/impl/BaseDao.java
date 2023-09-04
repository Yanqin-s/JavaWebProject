package com.itms.Dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import com.itms.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
	
	// 使用dbutils操作数据库
	private static QueryRunner queryRunner = new QueryRunner();
	
	/**
	 * update()方法用来执行：insert、update、delete语句
	 * @return 
	 */
	public static int update(String sql, Object ... args) {
		
		try {
			Connection conn = JdbcUtils.getConnection();
			return queryRunner.update(conn, sql, args);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 返回一个javaBean的语句
	 * @param <T> 返回的类型的泛型
	 * @param type 返回的对象的类型
	 * @param sql 执行的sql语句
	 * @param args sql对应的参数值
	 * @return
	 */
	public <T> T queryForOne(Class<T> type, String sql, Object...args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			
			return queryRunner.query(conn, sql, new BeanHandler<T>(type),args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConn(conn);
		}
		return null;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	
	public <T>List<T> queryForList(Class<T> type, String sql, Object ... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			JdbcUtils.closeConn(conn);
		}
		return null;
	}
	
	/**
	 * 返回执行一行一列的语句
	 * @param sql
	 * @param args sql对应的参数值
	 * @return
	 */
	
	public Object queryForSingleValue(String sql, Object...args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
