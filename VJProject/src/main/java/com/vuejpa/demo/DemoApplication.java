package com.vuejpa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoApplication {
	
	@RequestMapping(value="/index.do")
	public String restTest() {
		return "vue/index.html";
	}
}
