package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id) {
		sqlSession.insert("blog.insert",id);
	}

	public BlogVo findById(String blogId) {
		return sqlSession.selectOne("blog.findById",blogId);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
	}
}
