package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public Map<String, Object> getBlogContents(String blogId, Long categoryNo, Long postNo) {
		
		PostVo postVo = postRepository.findOne(blogId, categoryNo, postNo);
		BlogVo blogVo = blogRepository.findById(blogId);
		List<CategoryVo> categoryList = categoryRepository.findAllbyId(blogId);
		
		if(postVo != null) {
			categoryNo = postVo.getCategoryNo();
		}
		List<PostVo> postList = postRepository.findAllbyId(categoryNo);
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postVo", postVo);
		map.put("postList", postList);
		return map;
	}

	public void update(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}

	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryRepository.findAllbyId(blogId);
	}

	public Long writePost(PostVo postVo) {
		postRepository.insert(postVo);
		return postVo.getNo();
	}

	public List<CategoryVo> getCategoryListwithCount(String blogId) {
		return categoryRepository.findAllwithCountbyId(blogId);
	}

	public void addCategory(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);
	}
	
	@Transactional
	public void deleteCategory(CategoryVo categoryVo) {
		postRepository.deleteAll(categoryVo);
		categoryRepository.delete(categoryVo);
	}

}
