package cn.tedu.store2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store2.entity.User;
import cn.tedu.store2.service.IuserService;
import cn.tedu.store2.util.JsonResult;
import cn.tedu.store2.util.JwtUtil;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	@Autowired
	private IuserService userService;
	
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		userService.reg(user);
		
		return new JsonResult<Void>(1);
		
	}
	@RequestMapping("login")
	public JsonResult<String> login(String username,String password,HttpSession session,HttpServletRequest request){
		System.out.println(username+password);
		User reUser = userService.loginUser(username, password);
		
		/*
		 * String
		 * tokenString=JwtUtil.sign(reUser.getUsername(),String.valueOf(reUser.getUid())
		 * ); if(tokenString!=null) { return new JsonResult<String>(tokenString,2000); }
		 */
		
		//request.setAttribute("token", 809898989);
		
		  session.setAttribute("uid", reUser.getUid());
		  session.setAttribute("username", reUser.getUsername());
		 
		
		
		return new JsonResult<String>(2000);
		
	}
	@RequestMapping("changepassword")
	public JsonResult<Void> updatePassword(HttpSession session,String oldpassword,String newpassword){

		Integer uid=(Integer) session.getAttribute("uid");
		userService.changePassword(oldpassword,newpassword,uid,(String)session.getAttribute("username"));
		
		return new JsonResult<Void>(2000);
		
	}
}
