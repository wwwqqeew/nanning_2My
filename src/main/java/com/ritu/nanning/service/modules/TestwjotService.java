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

import com.ritu.nanning.entity.Testwjot;
import com.ritu.nanning.entity.TestwjotVo;
import com.ritu.nanning.entity.*;
import com.ritu.nanning.repository.TestwjotDao;
import com.ritu.nanning.repository.TestwjotImplDao;
import com.ritu.nanning.repository.*;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.utils.excel.ImportMsg;

/**
 * @function 外键测试业务层
 * @author cheng.G.Y
 * @date 2016-04-22
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class TestwjotService extends BaseService<Testwjot,Long>{

	private TestwjotDao testwjotDao;//JPA持久层对象

	private EntityImplDao testwjotImplDao;//持久层对象
	
	@Override
	protected EntityDao getEntityDao() {
		return this.testwjotDao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.testwjotDao;
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return testwjotImplDao;
	}
	

	@Autowired
	public void setTestwjotDao(TestwjotDao testwjotDao) {
		this.testwjotDao = testwjotDao;
	}
	
	@Autowired
	public void setTestwjotImplDao(EntityImplDao testwjotImplDao) {
		this.testwjotImplDao = testwjotImplDao;
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
		HashMap<String, Long> testwjHashMap = new HashMap<String, Long>();//testwj
		getTestwjHashMap();
		//外键List
		
		for (int i = 1; i < list.size(); i++) {
		//	for (int j = 0; j < list.get(i).size(); j++) {
				//System.out.print("["+j+"] " + list.get(i).get(j));
				
				int statrVal = 1;//读取数据的开始行数，第一列为列序号，第二为编号，数据第三开始
				Testwjot testwjot = new Testwjot();
				//Excel列表检测
				//主键
				//名称
				testwjot.setName(list.get(i).get(statrVal++).trim());
				//设置货品名称
				String testwjs = list.get(i).get(statrVal++).trim();
				Testwj Testwj = new Testwj(); 
				if (testwjs != null && !"".equals(testwjs)) {
					//不存在，则新增
					if (testwjHashMap.get(testwjs) != null) {
						Testwj.setId(testwjHashMap.get(Testwj));
					}
					Testwj.setName(testwjs);
					testwjot.setTestwj(Testwj);
				}else{
					msg.FaltSaveInt++;
					MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，Testwj名称["+Testwj+"]不能为空<br>\n");
					continue;
				}
				try {
					add(testwjot);
					msg.successSaveInt++;
					System.out.println("保存成功："+testwjot);
				} catch (Exception e) {
					msg.FaltSaveInt++;
					System.out.println("信息添加失败:"+testwjot);
				}
			}
		msg.setMsg(MSG.toString());
		System.out.println(MSG);
		return msg;
	
}

//外键List2

private TestwjDao testwjDao;

@Autowired
public void setTestwjDao(TestwjDao testwjDao) {
	this.testwjDao = testwjDao;
}
//初始化testwj
private HashMap<String, Long> getTestwjHashMap() {
	 List<Testwj> list = (List<Testwj>)testwjDao.findAll();
	 HashMap<String, Long> hs = new HashMap<String, Long>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				hs.put(list.get(i).getName(), list.get(i).getId());
			}
		}else{
			System.out.println("获取testwj哈希为空");
		}
		return hs;
}
}
//外键List2
