package cn.tedu.store2.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store2.entity.User;

/**
 * 用户的Mapper接口
 * 
 * @author 郭道军
 *
 */
public interface UserMapper {
	/**
	 * 保存用户的抽象方法
	 * 
	 * @param user传入user对象
	 * @return执行存入后返回执行的条数
	 */
	Integer save(User user);

	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param username 传入的用户名
	 * @return 查询到的用户信息
	 */
	User findByUsername(String username);
	
	Integer updatePassword(@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime,
			@Param("uid") Integer uid);
	

}
