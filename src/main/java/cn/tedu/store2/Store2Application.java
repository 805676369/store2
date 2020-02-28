package cn.tedu.store2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.store2.mapper")
public class Store2Application {

	public static void main(String[] args) {
		SpringApplication.run(Store2Application.class, args);
	}

}
