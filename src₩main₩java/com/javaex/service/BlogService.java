package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;

	// 해당 유저 블로그 정보가져오기
	public BlogVo blogMain(String id) {
		System.out.println("[BlogService.blogMain]");
		System.out.println(id);
		BlogVo blogVo = blogDao.blogMain(id);
		System.out.println(blogVo);
		return blogVo;
	}

	public int basicUpdate(String id, String blogTitle, MultipartFile file  ) {
		System.out.println("[BlogService.basicUpdate]");
		
		System.out.println();
		
		String SaveDir = "/Users/benedict/Java_Study/upload/";
		
		//파일 서버하드디스크에 저장
		//파일정보를 db에 저장?
		
		//원파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:"+orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:"+exName);	
			
	
		//저장파일이름(관리때문에 겹치지 않는 새이름 부여, 랜덤으로 이름부여 uuid)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+ exName;
		System.out.println("saveName:"+saveName);
		
		//파일패스
		String filePath = SaveDir + saveName;
		System.out.println("filePath:"+filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize:"+fileSize);
		
	
		
		//파일을 서버의 하드디스크에 저장
		//file restore on hdd
	      try {
	         byte[] fileData = file.getBytes();
	         OutputStream out = new FileOutputStream(filePath);
	         BufferedOutputStream bout = new BufferedOutputStream(out);
	         
	         bout.write(fileData);
	         bout.close();
	         
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
		  	BlogVo blogUpdateVo = new BlogVo(id, blogTitle, saveName);
			int count = blogDao.basicUpdate(blogUpdateVo);
			return count;
	      } 
				
				



}
