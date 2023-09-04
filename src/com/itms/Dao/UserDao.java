package com.itms.Dao;

import com.itms.Bean.User;

import java.util.List;

public interface UserDao {
	/**
	 * 根据用户名ID和用户密码判断登录
	 * 
	 * @param userID，password
	 * @return 如果返回null，说明用户名或密码错误
	 */
	public User login(String userID, String password);

	/**
	 * 查询所有员工的ID和姓名
	 * @return
	 */
	public List<User> queryAllUserNameAndID();

}
