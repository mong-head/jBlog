package com.douzone.jblog.controller.api;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@RestController("blogControllerApi") //다른 패키지에 같은 이름의 controller가 있기에 HandlerMapping시 id가 두 컨트롤러가 똑같이 들어갈 수 있어서 id를 임의로 설정해줌
@RequestMapping("/{id:(?!assets).*}/api")
public class BlogController {
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/checkid") //restful coding
	public JsonResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		
		UserVo userVo = userService.getUser(id);
		
		return JsonResult.success(userVo);
	}

}
