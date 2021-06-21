package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public PostVo findOne(String blogId, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		map.put("blogId",blogId);
		return sqlSession.selectOne("post.findOne",map);
	}

	public List<PostVo> findAllbyId(Long categoryNo) {
		return sqlSession.selectList("post.findAllbyId",categoryNo);
	}

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}

	public void deleteAll(CategoryVo categoryVo) {
		sqlSession.delete("post.deleteAll",categoryVo);
	}
}
