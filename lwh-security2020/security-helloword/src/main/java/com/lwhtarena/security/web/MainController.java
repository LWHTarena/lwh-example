package com.lwhtarena.security.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liwh
 * @Title: MainController
 * @Package com.lwhtarena.security.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/21 20:05
 */
@Api(value = "helloword",tags = "helloword")
@Controller
public class MainController {

	@ApiOperationSupport(author = "lwhtarena@163.com")
	@ApiOperation(value = "写文档注释我是认真的")
	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@ApiOperationSupport(author = "lwhtarena@163.com")
	@ApiOperation(value = "写文档注释我是认真的")
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}
