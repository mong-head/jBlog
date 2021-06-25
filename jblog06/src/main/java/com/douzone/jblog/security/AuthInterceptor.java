package com.douzone.jblog.security;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* 1. handler 종류 확인 */
		// defaultServlet Handler인 경우 : 정적 자원 접근
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		/* 2. casting */
		HandlerMethod handlerMethod = (HandlerMethod)handler; //annotation정보 알기 위해 casting
		
		/* 3. Handler Method의 @Auth정보 받아오기 */
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		/* 4. Handler Method의 @Auth 없는 경우 Type에 붙어 있는 지 확인*/
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		/* 5. Type과 Method 모두 @Auth 없는 경우*/
		if(auth == null) {
			return true;
		}
		
		/* 6. 인증(authentication) 여부 확인 */
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// 7. blog admin 확인
		String id = request.getRequestURL().toString().split("/")[4];
		id = URLDecoder.decode(id,"utf-8");		
		if(id.equals(authUser.getId()) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;
		
	}

}
