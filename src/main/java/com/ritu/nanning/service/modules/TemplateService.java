package com.ritu.nanning.service.modules;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.*;
import com.ritu.nanning.repository.*;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.DateUtil;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.utils.excel.ImportMsg;

/**
 * @function [例子]业务层
 * @author cheng.G.Y
 * @date 2016-06-14
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class TemplateService extends BaseService<Template,Integer>{

	private TemplateDao templateDao;//JPA持久层对象

	private EntityImplDao templateImplDao;//持久层对象
	
	@Override
	protected EntityDao getEntityDao() {
		return this.templateDao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.templateDao;
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return templateImplDao;
	}
	

	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}
	
	@Autowired
	public void setTemplateImplDao(EntityImplDao templateImplDao) {
		this.templateImplDao = templateImplDao;
	}
	

	/**
	 * 导入Excel
	 * @param list
	 */
	@Override
	@Transactional(readOnly = false)
	public ImportMsg importExcel(List<List<String>> list) {
		
//		List<List<String>> list = testPoiU("d:\\新文件名.xls");
//		System.out.println("数据条数："+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(i +" ");
//			for (int j = 0; j < list.get(i).size(); j++) {
//				System.out.print("["+j+"]" + list.get(i).get(j)+"  ");
//			}
//			System.out.println(" ");
//		}
		
		ImportMsg msg = new ImportMsg();
		msg.allDataInt = list.size()-1;
		StringBuffer MSG = new StringBuffer();

		//外键List
		//外键List
		
		for (int i = 1; i < list.size(); i++) {
		//	for (int j = 0; j < list.get(i).size(); j++) {
				//System.out.print("["+j+"] " + list.get(i).get(j));
				
				int statrVal = 1;//读取数据的开始行数，第一列为列序号，第二为编号，数据第三开始
				Template template = new Template();
				//Excel列表检测
					//主键
					//[输入框]
					template.setInput(list.get(i).get(statrVal++).trim());
					//时间类型检测
					//[时间]
					String dataInputDate = list.get(i).get(statrVal++).trim();//
					try {
						template.setInputDate(DateUtil.StringToDate(dataInputDate));//日期
					} catch (Exception e) {
						msg.FaltSaveInt++;
						MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，日期格式不正确<br>\n");
						continue;
					}
				try {
					add(template);
					msg.successSaveInt++;
					System.out.println("保存成功："+template);
				} catch (Exception e) {
					msg.FaltSaveInt++;
					System.out.println("信息添加失败:"+template);
				}
			}
		msg.setMsg(MSG.toString());
		System.out.println(MSG);
		return msg;
	
}
}
//外键List2
//外键List2
