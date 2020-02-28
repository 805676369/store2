package cn.tedu.store2.mapper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store2.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void save() throws Exception {
	User user=new User();
	user.setUsername("郭道军");
	user.setPassword("123456");
	Integer rows=userMapper.save(user);
	System.out.println(rows);
	
	}

}
