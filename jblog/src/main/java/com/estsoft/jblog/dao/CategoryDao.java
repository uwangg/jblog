package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(Long blog_no) {
		List<CategoryVo> list = sqlSession.selectList("category.selectList", blog_no);
		return list;
	}
	
	public Long insert(CategoryVo vo) {
		sqlSession.insert("category.insert",vo);
		return vo.getNo();
	}
	
	public void update(CategoryVo vo) {
		sqlSession.update("category.update", vo);
	}
	
	public CategoryVo get(Long no) {
		CategoryVo vo = sqlSession.selectOne("category.selectCat", no);
		return vo;
	}
	
	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}
}
