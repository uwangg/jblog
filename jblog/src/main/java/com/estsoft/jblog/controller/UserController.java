package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	// 회원 가입
	@RequestMapping("/joinform") // 회원가입 폼으로 이동
	public String joinform() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)	// 회원가입 처리부분 , post방식
	public String join(@Valid @ModelAttribute UserVo vo, BindingResult result, Model model) {
		
		if ( result.hasErrors() ) {	// 아이디, 이름, 패스워드부분이 빈칸인지 아닌지 검사
			model.addAttribute(result.getModel());
			model.addAttribute("vo",vo);
			return "/user/join";
		}

		userService.insert(vo);	// 전부 채웠을시 사용자 추가
		Long blog_no = blogService.insert(vo.getId());	// 사용자 생성시 사용자의 블로그 생성
		categoryService.insert(blog_no);	// 블로그 디폴트 카테고리 생성
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/checkid")	// 동일한 아이디가 존재하는지 검사 (ajax)
	@ResponseBody
	public Map<String, Object> checkId(
			@RequestParam(value="id", required=true, defaultValue="") String id) {
		
		UserVo vo = userService.getUser(id);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", vo == null);
		return map;
	}
	
	@RequestMapping("/joinsuccess")	// 회원가입 성공시 폼
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	
	
	// 로그인 부분
	@RequestMapping("/loginform")	// 로그인 폼으로 이동
	public String loginform() {
		return "user/login";
	}
	
	@RequestMapping("/loginfail")	// 로그인 실패시
	public String loginfail() {
		return "user/login_fail";
	}

}
