package com.ritu.nanning.web.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.Track;
import com.ritu.nanning.service.modules.DemoService;
import com.ritu.nanning.service.modules.TrackService;
import com.ritu.nanning.utils.base.BaseEditControl;
import com.ritu.nanning.utils.base.BaseService;

@Controller
@RequestMapping(value = "/edittrack")
public class EditTrackController extends BaseEditControl<Track,Long>{
	
	private TrackService trackService;

	
	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	@Override
	protected BaseService getEntityService() {
		return trackService;
	}

	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "Track";
	}

}
