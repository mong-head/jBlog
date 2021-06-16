package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id) {
		sqlSession.insert("blog.insert",id);
	}
}
