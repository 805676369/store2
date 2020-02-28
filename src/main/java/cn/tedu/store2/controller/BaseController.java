package cn.tedu.store2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store2.service.ex.InsertException;
import cn.tedu.store2.service.ex.PasswordErrorException;
import cn.tedu.store2.service.ex.ServiceException;
import cn.tedu.store2.service.ex.UserNotFoundException;
import cn.tedu.store2.service.ex.UsernameConflictException;
import cn.tedu.store2.util.JsonResult;

public class BaseController {
	
	public Integer gitUidFromSession(HttpSession session) {
		
		return Integer.valueOf(session.getAttribute("uid").toString());
		
	}
	
	public String gitUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
	
	
	
	
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handJsonResult(Throwable e){
		JsonResult<Void> jrJsonResult=new JsonResult<Void>(e);
		if (e instanceof UsernameConflictException) {
		jrJsonResult.setState(2);
		}else if(e instanceof InsertException){
			jrJsonResult.setState(3);
		}else if(e instanceof UserNotFoundException) {
			jrJsonResult.setState(4);
		}else if(e instanceof PasswordErrorException) {
			jrJsonResult.setState(5);
		}
		
		
		return jrJsonResult;
		
	}

}
