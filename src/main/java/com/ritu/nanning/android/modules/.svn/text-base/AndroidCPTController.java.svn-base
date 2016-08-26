package com.ritu.nanning.android.modules;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.utils.sy;

@Controller
@RequestMapping(value = "/cpt")
public class AndroidCPTController {


	@ResponseBody
	@RequestMapping(value = "open",  produces = "text/html;charset=UTF-8")
	public String checkpw(@RequestParam(value = "url", required = false) String url) {
		System.out.println("进入了方法");
		if (url.indexOf(" ") >= 0) {
			url =  "\" \" \""+url+"\"";
		}
		sy.o(url);
		try {
			if (!url.isEmpty()) {
				Runtime.getRuntime().exec("cmd.exe /c start "+url);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "地址：["+url+"]无效，请检查。";
		}
		return "1";
	}
	
}

