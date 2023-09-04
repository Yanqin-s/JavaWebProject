package com.itms.service.impl;


import com.itms.Bean.User;
import com.itms.Dao.UserDao;
import com.itms.Dao.impl.UserDaoImpl;
import com.itms.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

	/**
	 * 登录需要操作数据库，所以要有一个UserDao对象
	 */
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		// 用户登录，调用userDAO中的login，根据用户的ID和密码查询数据库
		String userID = user.getU_ID();
		String pwd = user.getU_PW();

		return userDao.login(userID, pwd);
	}

	@Override
	public List<User> queryAllUserNameAndID() {
		return userDao.queryAllUserNameAndID();
	}

}
