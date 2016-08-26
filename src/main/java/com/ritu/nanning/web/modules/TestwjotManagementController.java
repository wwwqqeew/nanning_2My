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

import com.ritu.nanning.entity.Testwjot;
import com.ritu.nanning.entity.TestwjotVo;
import com.ritu.nanning.service.modules.TestwjotService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseControl;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseService;

/**
 * @function 外键测试控制层
 * @author cheng.G.Y
 * @date 2016-04-22
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/testwjotmanagement")
public class TestwjotManagementController extends BaseControl<Testwjot,Long>{
	
	private TestwjotService testwjotService;//业务层对象
	private TestwjotVo testwjotVo = new TestwjotVo();

	@Autowired
	public void setTestwjotService(TestwjotService testwjotService) {
		this.testwjotService = testwjotService;
	}

	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/TestwjotManagement";
	}
	
	@Override
	protected BaseService getEntityService() {
		return testwjotService;
	}

	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return testwjotVo;
	}

}
