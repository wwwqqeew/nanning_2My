package com.ritu.nanning.web.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.entity.TestwjVo;
import com.ritu.nanning.service.modules.TestwjService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseControl;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseService;

/**
 * @function 外键测试控制层
 * @author cheng.G.Y
 * @date 2016-04-17
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/testwjmanagement")
public class TestwjManagementController extends BaseControl<Testwj,Long>{
	
	private TestwjService testwjService;//业务层对象
	private TestwjVo testwjVo = new TestwjVo();

	@Autowired
	public void setTestwjService(TestwjService testwjService) {
		this.testwjService = testwjService;
	}

	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/TestwjManagement";
	}
	
	@Override
	protected BaseService getEntityService() {
		return testwjService;
	}

	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return testwjVo;
	}

}
