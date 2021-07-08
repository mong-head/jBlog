package com.douzone.jblog.controller.api;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@RestController("blogControllerApi") //다른 패키지에 같은 이름의 controller가 있기에 HandlerMapping시 id가 두 컨트롤러가 똑같이 들어갈 수 있어서 id를 임의로 설정해줌
@RequestMapping("/{id:(?!assets|ejs).*}/api")
public class BlogController {
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping(value="/checkid") //restful coding
	public JsonResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		
		UserVo userVo = userService.getUser(id);
		
		return JsonResult.success(userVo);
	}
	
	// admin page - category
	
	@GetMapping(value="/admin/category")
	public JsonResult readCategory(@PathVariable("id") String id) {
		List<CategoryVo> categoryList = blogService.getCategoryListwithCount(id);
		return JsonResult.success(categoryList);	
	}
	
	@PostMapping(value="/admin/category")
	public JsonResult createCategory(@PathVariable("id") String id, @RequestBody CategoryVo categoryVo) {
		if (categoryVo.getName() == "") {
			return JsonResult.fail("이름 없음");
		}
		categoryVo.setBlogId(id);
		blogService.addCategory(categoryVo);
		return JsonResult.success(categoryVo);
	}
	
	@DeleteMapping(value="/admin/category")
	public JsonResult deleteCategory(@PathVariable("id") String id
			,@RequestParam(value = "no", required = true, defaultValue = "") Long categoryNo){
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(id);
		categoryVo.setNo(categoryNo);

		blogService.deleteCategory(categoryVo);
		return JsonResult.success(categoryNo);
	}

}
