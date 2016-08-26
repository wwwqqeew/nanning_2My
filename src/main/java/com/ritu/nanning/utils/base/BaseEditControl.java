package com.ritu.nanning.utils.base;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.DemoVo;
import com.ritu.nanning.service.modules.DemoService;
import com.ritu.nanning.utils.EncodeUtil;
import com.ritu.nanning.utils.ExportExcelUtil;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.o;

@Controller
public abstract class BaseEditControl <E,PK extends Serializable>{
	
	protected abstract BaseService  getEntityService();
	
	protected abstract String  getEntityName();
	

	/**
	 * 页面请求POST
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/Edit"+getEntityName();
	}
	
	/**
	 * 页面请求GET
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String htmlGet(Model model) {
		return "/modules/Edit"+getEntityName();
	}
	
	/**
	 * 显示信息界面
	 * @param User.id 
	 * @return 结果
	 */
	@RequestMapping(value = "showAdd")
	public String showAdd() {
		return "/modules/Edit"+getEntityName();
	}
	
	/**
	 * 更新
	 * @param demo 要更新的对象
	 * @return 更新结果
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String update(@RequestBody E demo) {
		getEntityService().update(demo);
		return JsonBuilder.toJson(true);
	}

	/**
	 * 根据ID查找 
	 * @param id ID
	 * @return 查找结果
	 */
	@RequestMapping(value = "findOneById", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String findOneById(@RequestParam(value = "id", required = false) Long id,ModelMap model) {
		model.addAttribute("entity",getEntityService().findById(id));
		return "/modules/Edit"+getEntityName();
	}
	
	/**
	 * 保存对象
	 * @param demo 要保存的对象
	 * @return 保存结果
	 */
	@ResponseBody
	@RequestMapping(value = "newone", produces = "text/html;charset=UTF-8")
	public String newone(@RequestBody E demo) {
		o.o(demo);
		getEntityService().add(demo);
		return JsonBuilder.toJson(true);
	}
	
	/**
	 * 显示信息界面
	 * @param User.id 
	 * @return 结果
	 */
	@RequestMapping(value = "show")
	public String show(@RequestParam("id")  Long id,ModelMap model) {
		model.addAttribute("entity",getEntityService().findById(id));
		return "/modules/Show"+getEntityName();
	}
}
