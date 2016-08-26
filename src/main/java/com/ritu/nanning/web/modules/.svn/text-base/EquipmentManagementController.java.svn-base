package com.ritu.nanning.web.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.Equipment;
import com.ritu.nanning.service.modules.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentManagementController {

	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping(value = "/equipmentManage", method = RequestMethod.GET)
	public String equipmentManage() {
		return "modules/equipmentManage";
	}

	@RequestMapping(value = "/equipmentList", method = RequestMethod.GET)
	@ResponseBody
	public List<Equipment> equipmentList(int page, int size, boolean isIdAsc) {
		return equipmentService.findList(page, size, isIdAsc).getContent();
	}
}
