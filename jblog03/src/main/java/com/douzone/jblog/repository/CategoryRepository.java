package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert",categoryVo);
	}

	public List<CategoryVo> findAllbyId(String blogId) {
		return sqlSession.selectList("category.findAllbyId",blogId);
	}

	public List<CategoryVo> findAllwithCountbyId(String blogId) {
		return sqlSession.selectList("category.findAllwithCountbyId",blogId);
	}

	public void delete(CategoryVo categoryVo) {
		sqlSession.delete("category.delete", categoryVo);
	}

}
