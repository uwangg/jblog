package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public BlogVo get(String user_id) {
		BlogVo vo = blogDao.get(user_id);
		return vo;
	}
	
	public Long insert(String user_id) {
		BlogVo vo = new BlogVo();
		vo.setUser_id(user_id);
		vo.setTitle(user_id+"이야기");
		Long blog_no = blogDao.insert(vo);
		return blog_no;
	}
	
	public void update(BlogVo vo) {
		blogDao.update(vo);
	}
}
