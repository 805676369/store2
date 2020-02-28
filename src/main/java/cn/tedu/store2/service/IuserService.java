package cn.tedu.store2.service;

import cn.tedu.store2.entity.User;

public interface IuserService {
	/**
	 * 业务层接口注册的方法
	 * @param user
	 */
	void reg(User user);
	/**
	 * 业务层接口登录方法
	 * @param 用户输入的用户名和密码
	 * @return 返回User对象
	 */
	User loginUser(String username,String password);
	
	Integer changePassword(String oldpasswordString,String newpasswordString,Integer uid,String username);
	
}
