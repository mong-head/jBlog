package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;

	public void insert(UserVo userVo) {
		sqlSession.insert("user.insert",userVo);
	}

	public UserVo findByIdPassword(String id, String password) {
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("password", password);
		return sqlSession.selectOne("user.findByIdPassword",map);
	}
}
