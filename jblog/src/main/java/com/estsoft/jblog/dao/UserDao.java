package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(UserVo vo) {
		UserVo userVo = sqlSession.selectOne("user.selectByUserVo",vo);
		return userVo;
	}
	
	public UserVo getUser(String id) {
		UserVo vo = sqlSession.selectOne("user.selectById", id);
		return vo;
	}
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert",vo);
	}
}
