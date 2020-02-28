package cn.tedu.store2.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store2.util.LoginInterceptor;
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("进入拦截器的配置文件");
		HandlerInterceptor interceptor=new LoginInterceptor();
		List<String> paths=new ArrayList<String>();
		paths.add("/js/**");
		paths.add("/css/**");
		paths.add("/images/**");
		paths.add("/bootstrap3/**");
		
		paths.add("/web/register.html");
		paths.add("/web/login.html");
		
		
		  paths.add("/users/reg");
		  paths.add("/users/login");
		 
	
	registry.addInterceptor(interceptor)
	.addPathPatterns("/**")
	.excludePathPatterns(paths);
	
	}

}
