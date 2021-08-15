package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired 
	private SqlSession sqlSession; 

	
	//로그인사용자 정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser]");
		System.out.println(userVo);

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println(authUser);
		return authUser;
	}

	public int insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		System.out.println(userVo);

		return sqlSession.insert("user.insertUser", userVo);
	}



}

