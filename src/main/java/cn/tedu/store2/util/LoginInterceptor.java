package cn.tedu.store2.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		
		
		  if (session.getAttribute("uid")==null) {
			  response.sendRedirect("/web/login.html");
			  return false;
		  }
		 
		/*
		 * String token=request.getHeader("accessToken"); if (null!=token) { boolean
		 * result=JwtUtil.verify(token); if (result) { return true; } }
		 */
		
		
	//	System.out.println("进入拦截器的拦截方法了");
		
	//	JsonResult<String> result=new JsonResult<String>("测试token失败");
		
				return true;
		
	}
	
	

}
