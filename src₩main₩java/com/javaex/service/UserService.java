package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired 
	private UserDao userDao;
	
	//로그인 사용자 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");

		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
	
	// 회원가입
		public int insertUser(UserVo userVo) {
			System.out.println("[UserService.insertUser()]");

			return userDao.insertUser(userVo);
		}
	
		
		
}
