package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 블로그 리스트 가져오기
	public List<BlogVo> getList() {
		List<BlogVo> list = sqlSession.selectList("blog.selectList");
		return list;
	}
	
	// vo 추가
	public Long insert(BlogVo vo) {
		sqlSession.insert("blog.insert", vo);
		return vo.getNo();
	}
	
	// 사용자ID로 블로그 정보 가져오기
	public BlogVo get(String user_id) {
		BlogVo vo = sqlSession.selectOne("blog.selectById", user_id);
		return vo;
	}
	
	// 프로필 업데이트
	public void update(BlogVo vo) {
		System.out.println("dao vo : " + vo);
		sqlSession.update("blog.update", vo);
	}

}
