package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets|ejs).*}")
public class BlogController {

	@Autowired
	private ServletContext application;

	@Autowired
	private BlogService blogService;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}

		Map<String, Object> result = blogService.getBlogContents(id, categoryNo, postNo);
		application.setAttribute("authBlogVo", result.get("blogVo"));
		model.addAttribute("blogVo", result.get("blogVo"));
		model.addAttribute("categoryList", result.get("categoryList"));
		model.addAttribute("postVo", result.get("postVo"));
		model.addAttribute("postList", result.get("postList"));

		return "blog/main";
	}

	// admin page

	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@ModelAttribute @PathVariable("id") String id, Model model) {
		// category list
		List<CategoryVo> categoryList = blogService.getCategoryListwithCount(id);
		model.addAttribute("categoryList", categoryList);
		
		model.addAttribute("menu", "basic");
		return "blog/admin/basic";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasicUpdate(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo,
			@RequestParam("logo_file") MultipartFile file, Model model) {
		String url = fileUploadService.restore(file);
		blogVo.setId(id);
		blogVo.setLogo(url);
		blogService.update(blogVo);

		// 고치고 싶은 부분
		if (url == null) {
			url = ((BlogVo) application.getAttribute("authBlogVo")).getLogo();
			blogVo.setLogo(url);
		}
		application.setAttribute("authBlogVo", blogVo);

		return "redirect:/{id}/admin/basic";
	}

	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		// category list
//		List<CategoryVo> categoryList = blogService.getCategoryListwithCount(id);
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("listLength", categoryList.size());

		model.addAttribute("menu", "category");
		return "blog/admin/category";
	}

	@Auth
	@RequestMapping("/admin/category/delete")
	public String adminCategoryDelete(@PathVariable("id") String id,
			@RequestParam(value = "no", required = true, defaultValue = "") Long categoryNo) {

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(id);
		categoryVo.setNo(categoryNo);

		blogService.deleteCategory(categoryVo);
		return "redirect:/{id}/admin/category";
	}

	@Auth
	@RequestMapping("/admin/category/add")
	public String adminCategoryAdd(@PathVariable("id") String id, CategoryVo categoryVo) {
		if (categoryVo.getName() == "") {
			return "redirect:/{id}/admin/category";
		}
		categoryVo.setBlogId(id);
		blogService.addCategory(categoryVo);
		return "redirect:/{id}/admin/category";
	}

	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model) {

		List<CategoryVo> categoryList = blogService.getCategoryList(id);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("menu", "write");
		return "blog/admin/write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo,
			@RequestParam(value = "category", required = true, defaultValue = "") Long categoryNo, Model model) {

		postVo.setCategoryNo(categoryNo);
		Long postNo = blogService.writePost(postVo);

		return "redirect:/{id}/" + categoryNo + "/" + postNo;
	}
}
