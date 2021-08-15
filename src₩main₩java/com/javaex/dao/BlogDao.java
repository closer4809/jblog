package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired SqlSession sqlSession;

	//해당 유저 블로그 정보가져오기
	
	public BlogVo blogMain(String id) {
		System.out.println(id);
		BlogVo blogVo = sqlSession.selectOne("blog.selectBlog", id);
		System.out.println(blogVo);
		return blogVo;
	}
	
	public int basicUpdate(BlogVo blogVo) {
		
		System.out.println("[BlogDao.basicUpdate()]");
		
		int count = sqlSession.update("blog.basicUpdate", blogVo);
		
		return count;
		
	}

}
