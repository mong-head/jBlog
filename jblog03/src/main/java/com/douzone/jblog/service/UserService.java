package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
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
		userRepository.insert(userVo);
		blogRepository.insert(userVo.getId());
		categoryRepository.insert(userVo.getId());
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdPassword(id, password);
	}
	
}
