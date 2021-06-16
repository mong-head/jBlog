package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(String blogId) {
		sqlSession.insert("category.insert",blogId);
	}

}
