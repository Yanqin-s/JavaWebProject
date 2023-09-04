package com.itms.Dao.impl;

import com.itms.Bean.User;
import com.itms.Dao.UserDao;
import com.itms.Dao.impl.BaseDao;

import java.util.List;

// 用户操作数据库的一些方法，实现staffDao接口的一些方法，login：根据用户的ID和密码查询是否有这个用户（用户的登录操作）
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User login(String userID, String passqord) {
		// TODO Auto-generated method stub
		String sql = "select * from user where u_ID=? and u_PW=? ;";
		return queryForOne(User.class, sql, userID, passqord);
	}

	@Override
	public List<User> queryAllUserNameAndID() {
		String sql = "select `u_Name`,`u_ID` from `user`;";
		return queryForList(User.class, sql);
	}


}
