package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm]");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login]");
		UserVo authUser = userService.getUser(userVo);
		System.out.println(authUser);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println("로그인성공");
			return "redirect:/";
		} else {
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}

	}

	// 로그아웃
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {		
		System.out.println("[UserController.logout]");
		System.out.println(session);
		session.removeAttribute("authUser");
		System.out.println(session);
		// 세션에 할당되는 메모리를 삭제
		session.invalidate();
		System.out.println(session);
		return "redirect:/";

	}
	
	// 조인폼
	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm]");

		return "user/joinForm";
	}
	
	// 회원가입
		@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
		public String join(@ModelAttribute UserVo userVo) {
			System.out.println("[UserController.join()]");

			userService.insertUser(userVo);
			System.out.println(userVo);
			
			
			return "user/joinSuccess";

		}
		
	
}
