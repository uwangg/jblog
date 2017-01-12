package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getList(Long category_no) {
		List<PostVo> list = sqlSession.selectList("post.selectList", category_no);
		return list;
	}
	
	public PostVo get(Long no) {
		PostVo vo = sqlSession.selectOne("post.selectByNo", no);
		return vo;
	}
	
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert",vo);
	}
	
	public void delete(Long category_no) {
		sqlSession.delete("post.delete", category_no);
	}
}
