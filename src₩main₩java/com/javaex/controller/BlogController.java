package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;

	// 메인 블로그 불러오기
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogMain(Model model, @PathVariable("id") String id) {
		System.out.println("[BlogController.blog()]");
		System.out.println(id);
		BlogVo blogVo = blogService.blogMain(id);
		System.out.println(blogVo);
		if (blogVo != null) {
			// System.out.println(blogVo);
			model.addAttribute("blogVo", blogVo);

			return "blog/blog-main";
		} else {
			return "redirect:/";
		}

	}

	// 기본 블로그관리창(유저정보 불러옴)
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasic(Model model, @PathVariable("id") String id) {
		System.out.println("[BlogController.adminBasic()]");
		BlogVo blogVo = blogService.blogMain(id);
		model.addAttribute("blogVo", blogVo);
		System.out.println("어드민폼으로 blogVo 전송");
		System.out.println(blogVo);
		return "blog/admin/blog-admin-basic";
	}

	//블로그기본 수정
	@RequestMapping(value = "/{id}/admin/basic/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String basicUpdate(@PathVariable("id") String id, @RequestParam("file") MultipartFile file, @RequestParam("blogTitle") String blogTitle) {
		System.out.println("[BlogController.adminBasic()]");
		
		blogService.basicUpdate(id, blogTitle, file);
		
		
		

		
		
		return "";
	}

}
