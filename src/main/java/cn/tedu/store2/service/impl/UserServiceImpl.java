package cn.tedu.store2.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store2.entity.User;
import cn.tedu.store2.mapper.UserMapper;
import cn.tedu.store2.service.IuserService;
import cn.tedu.store2.service.ex.InsertException;
import cn.tedu.store2.service.ex.PasswordErrorException;
import cn.tedu.store2.service.ex.UserDeleteException;
import cn.tedu.store2.service.ex.UserNotFoundException;
import cn.tedu.store2.service.ex.UsernameConflictException;
@Service
public class UserServiceImpl implements IuserService{

	@Autowired
	private UserMapper usermapper;
	
	
	@Override
	public void reg(User user) {
		String username=user.getUsername();
		User result = usermapper.findByUsername(username);
		if (result!=null) {
			throw new UsernameConflictException("用户名"+username+"已经注册，请更换用户名");
		}
		
		String salt=UUID.randomUUID().toString().toUpperCase();
		String md5PasswordString=getMd5passwordString(user.getPassword(), salt);
		user.setPassword(md5PasswordString);
		user.setSalt(salt);
		user.setIsDelete(0);
		Date nowDate=new Date();
		user.setCreatedTime(nowDate);
		user.setCreatedUser(username);
		user.setModifiedTime(nowDate);
		user.setModifiedUser(username);
		Integer rowInteger = usermapper.save(user);
		if (rowInteger!=1) {
			throw new InsertException("插入数据出现未知错误");
		}
	}
	
	private String getMd5passwordString(String password,String salt) {
		String str=salt+password+salt;
		for (int i = 0; i < 5; i++) {
			str=DigestUtils.md5DigestAsHex(str.getBytes());
		}
		
		return str;
		
	}

	@Override
	public User loginUser(String username, String password) {
		User reUser=usermapper.findByUsername(username);
		if (reUser==null) {
			throw new UserNotFoundException(username+"不存在，请重新输入");
			}	
		if (!getMd5passwordString(password, reUser.getSalt()).equals(reUser.getPassword())) {
			throw new PasswordErrorException("你输入的密码错误，请重新输入");
		}
		if(reUser.getIsDelete()==1) {
			throw new UserDeleteException("你已经被删除");
		}
		
		reUser.setCreatedTime(null);
		reUser.setCreatedUser(null);
		reUser.setPassword(null);
		reUser.setSalt(null);
		
		
		return reUser;
	}

	@Override
	public Integer changePassword(String oldpasswordString, String newpasswordString, Integer uid, String username) {
		//1.根据uid从数据库中查询有没有这个数据
		System.out.println("old"+oldpasswordString+"new "+newpasswordString );
		User result=usermapper.findByUsername(username);
		if (result==null) {
			throw new UserNotFoundException("用户不存在");
		}
		//2,比对旧密码和数据库密码是否一致。
		if (!result.getPassword().equals(getMd5passwordString(oldpasswordString, result.getSalt()))) {
			throw new PasswordErrorException("原始密码不正确");
		}//3.修改
	Integer updatePassword = usermapper.updatePassword(getMd5passwordString(newpasswordString, result.getSalt()), username, new Date(), uid);
		System.out.println(updatePassword);
	if(updatePassword!=1) {
			throw new PasswordErrorException("插入密码错误");
		}
		return updatePassword;
	}

}
