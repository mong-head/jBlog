package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public void join(UserVo userVo) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(userVo.getId());
		
		userRepository.insert(userVo);
		blogRepository.insert(userVo.getId());
		categoryRepository.insert(categoryVo);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdPassword(id, password);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
}
