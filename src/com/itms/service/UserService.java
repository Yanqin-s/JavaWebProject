
/*
 * @Author: Amber.Shuai
 * 
 * @Github: https://github.com/Yanqin-s
 * 
 * @Date: 2021-12-07 11:22:10
 * 
 * @LastEditord: Amber.shuai
 * 
 * @LastEditTime: 2021-12-07 11:22:11
 */
package com.itms.service;
import com.itms.Bean.User;

import java.util.List;

public interface UserService {
	/**
	 * 用户登录功能
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 查询所有用户的ID和姓名
	 * @return
	 */
	public List<User> queryAllUserNameAndID();
}
