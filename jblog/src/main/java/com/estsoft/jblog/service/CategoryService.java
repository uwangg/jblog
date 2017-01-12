package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getList(Long blog_no) {
		List<CategoryVo> list = categoryDao.getList(blog_no);
		return list;
	}
	
	public void insert(Long blog_no) {
		CategoryVo vo = new CategoryVo();
		vo.setBlog_no(blog_no);
		vo.setName("미분류");
		vo.setDescription("카테고리를 지정하지 않은 경우");
		categoryDao.insert(vo);
	}
	
	public Long insert(CategoryVo vo) {
		Long no = categoryDao.insert(vo);
		return no;
	}
	
	public void update(CategoryVo vo) {
		Long posting = vo.getPosting();
		posting ++;
		vo.setPosting(posting);
		categoryDao.update(vo);
	}
	
	public CategoryVo get(Long no) {
		CategoryVo vo = categoryDao.get(no);
		return vo;
	}
	
	public void delete(Long no) {
		categoryDao.delete(no);
	}
}
