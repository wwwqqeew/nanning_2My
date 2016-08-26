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

import com.ritu.nanning.entity.Track;
import com.ritu.nanning.entity.TrackVo;
import com.ritu.nanning.service.modules.TrackService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseControl;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseService;

/**
 * @function 轨迹控制层
 * @author cheng.G.Y
 * @date 2016-04-03
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/trackmanagement")
public class TrackManagementController extends BaseControl<Track,Long>{
	
	private TrackService trackService;//业务层对象
	private TrackVo trackVo = new TrackVo();

	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/TrackManagement";
	}
	
	@Override
	protected BaseService getEntityService() {
		return trackService;
	}

	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return trackVo;
	}

}
