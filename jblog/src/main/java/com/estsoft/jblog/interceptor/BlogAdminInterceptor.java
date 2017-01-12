package com.estsoft.jblog.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

public class BlogAdminInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String url = URLDecoder.decode( request.getRequestURI(), "UTF-8" );
		String urlList[] = url.split("/");
		
		if(urlList.length <= 3) {	// id를 넣지 않은경우
			response.sendRedirect(request.getContextPath() + "/main");
			return false;
		}
		BlogVo vo = blogService.get(urlList[3]);
		// 블로그 아이디 체크
		if(vo == null) {	// 존재하지 않는 블로그 아이디일 경우
			response.sendRedirect(request.getContextPath() + "/main");
			return false;
		}

		if(urlList.length >= 5) {	// 세션의 아이디와 현재 접속중인 블로그 아이디가 동일한지 체크
			HttpSession session = request.getSession();

			if(session == null) {	// 세션이 없을 경우
				response.sendRedirect(request.getContextPath() + "/blog/" + urlList[3]);
				return false;
			}

			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				response.sendRedirect(request.getContextPath() + "/blog/" + urlList[3]);
				return false;
			}

			//System.out.println("authUser.id = "+authUser.getId()+", urlList[3] = "+urlList[3]);
			if(!authUser.getId().equals(urlList[3])) {	// blog admin이 아닌경우
				response.sendRedirect(request.getContextPath() + "/blog/" + urlList[3]);
				return false;
			}
		}

		request.setAttribute("blogVo", blogService.get(urlList[3]));
		return true;
	}

}
