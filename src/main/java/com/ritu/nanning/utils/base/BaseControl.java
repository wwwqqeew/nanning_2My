package com.ritu.nanning.utils.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.DemoVo;
import com.ritu.nanning.utils.EncodeUtil;
import com.ritu.nanning.utils.ExportExcelUtil;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.excel.ImportMsg;
import com.ritu.nanning.utils.excel.ReadExcel;

@Controller
public abstract class BaseControl <E,PK extends Serializable>{
	
	protected abstract BaseService  getEntityService();
	protected abstract BaseEntityVo getBaseEntityVo();
	
	/**
	 * 导入功能
	 * @param myfiles
	 * @param request
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "importAction", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String upload(@RequestParam(value = "myfiles", required = false) MultipartFile[] myfiles,
			HttpServletRequest request) {
		int ardInDbInt = 0;
//		System.out.println("进入导入方法");
		Map<Integer,String> errorMap = new HashMap<Integer,String>();
		Map<Integer,String> map = new HashMap<Integer,String>();
		String MSG = "";
			for (MultipartFile myfile : myfiles) {
				if (myfile.isEmpty()) {
					System.out.println("上传文件为空");
				} else {
						List<List<String>> list = null;
						try {
							ReadExcel po = new ReadExcel();
							list = po.read(myfile.getOriginalFilename(),myfile.getInputStream());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						ImportMsg s = new ImportMsg();
						s = getEntityService().importExcel(list);
						s.setArdInDbInt(ardInDbInt);
//						s.setSuccessSaveInt(succeed);
//						s.setFaltSaveInt(faild);
						request.setAttribute("data", "0");
						request.setAttribute("MSG", s.getMsg());
						request.setAttribute("AllDataInt", s.getAllDataInt());
						request.setAttribute("ArdInDbInt", s.getArdInDbInt());
						request.setAttribute("FaltSaveInt", s.getFaltSaveInt());
						request.setAttribute("SuccessSaveInt",s.getSuccessSaveInt());
				 
				}
			}
		 
		return "/modules/import";
	}
	
	//显示导入页面
	@RequestMapping(value = "importShow", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String importShow(ModelMap model)
	{
		return "/modules/import";
	}
	
	/**
	 * 根据页码查询对象列表
	 * @param page 页码
	 * @return 查询结果(Page对象列表)
	 */
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String search(@RequestParam(value = "page", required = false) int page) {
		Page<Demo> results = getEntityService().findAll(page);
		return JsonBuilder.toJson(true, results.getContent(), page,results.getTotalPages());
	}
	
	/**
	 * 根据页面类属性获取所有数据
	 * @return 查询结果（对象List列表）
	 */
	@ResponseBody
	@RequestMapping(value = "findList", produces = "text/html;charset=UTF-8")
	public String findList(String propertys) {
		BaseEntityVo demoVo = getBaseEntityVo().buildEntityFromJson(propertys, getBaseEntityVo().getClass());
		return getEntityService().findList(demoVo, true);
	}
	
	/**
	 * 根据ID删除
	 * @param id ID
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String delete(@RequestParam(value = "id", required = false) Long id) {
		getEntityService().delete(id);
		return JsonBuilder.toJson(true);
	}

	/**
	 * 根据ID列表删除
	 * @param idList ID列表 (String)
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping(value = "deleteByIdList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteByIds(
			@RequestParam(value = "idList", required = false) String idList) {
		String[] theIds = idList.split(",");
		for (String x : theIds) {
			getEntityService().delete(Long.valueOf(x.trim()));
		}
		return JsonBuilder.toJson(true);
	}
	
	/**
	 * 根据页面类属性查询（多属性查询）
	 * @return 查询结果（Page对象列表）
	 */
	@ResponseBody
	@RequestMapping(value = "findByPropertys", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String findByNumberAndLoginNameAndRole_id(String propertys) {
		BaseEntityVo demoVo = getBaseEntityVo().buildEntityFromJson(propertys, getBaseEntityVo().getClass());
		return getEntityService().findByPropertys(demoVo ,demoVo.getPage(), 10, true);
	}
	
	/**
	 * 导出Excel
	 * @param propertys 查询属性 {"name":"3"}
	 * @param exportTitle 导出的列及列名称  {"id":"编号","name":"名称1","name2":"名称2"}
	 * @param response
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "export", produces = "text/html;charset=UTF-8")
	public void export(@RequestParam(value = "propertys", required = false)String propertys,
			@RequestParam(value = "exportTitle", required = false)String exportTitle, 
			HttpServletResponse response, 
			HttpServletRequest request) {
		BaseEntityVo demoVo = getBaseEntityVo().buildEntityFromJson(propertys, getBaseEntityVo().getClass());
		String encodeStr = exportTitle;
		//EncodeUtil.encodeToUtf8(exportTitle); GET方式乱码的时候用
		getEntityService().dd();
		ExportExcelUtil.exportExcel(response, getEntityService().findByPropertys(demoVo, true), getBaseEntityVo(), getBaseEntityVo().getExporttitle(), encodeStr);
	}
}
