package com.ritu.nanning.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.entity.TestwjVo;
import com.ritu.nanning.repository.TestwjDao;
import com.ritu.nanning.repository.TestwjImplDao;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.utils.excel.ImportMsg;

/**
 * @function 外键测试业务层
 * @author cheng.G.Y
 * @date 2016-04-17
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class TestwjService extends BaseService<Testwj,Long>{

	private TestwjDao testwjDao;//JPA持久层对象

	private EntityImplDao testwjImplDao;//持久层对象
	
	@Override
	protected EntityDao getEntityDao() {
		return this.testwjDao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.testwjDao;
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return testwjImplDao;
	}
	

	@Autowired
	public void setTestwjDao(TestwjDao testwjDao) {
		this.testwjDao = testwjDao;
	}
	
	@Autowired
	public void setTestwjImplDao(EntityImplDao testwjImplDao) {
		this.testwjImplDao = testwjImplDao;
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

		for (int i = 1; i < list.size(); i++) {
		//	for (int j = 0; j < list.get(i).size(); j++) {
				//System.out.print("["+j+"] " + list.get(i).get(j));
				
				int statrVal = 1;//读取数据的开始行数，第一列为列序号，第二为编号，数据第三开始
				Testwj testwj = new Testwj();
				//Excel列表检测
				//主键
				//名称
				testwj.setName(list.get(i).get(statrVal++).trim());
				
				try {
					add(testwj);
					msg.successSaveInt++;
					System.out.println("保存成功："+testwj);
				} catch (Exception e) {
					msg.FaltSaveInt++;
					System.out.println("信息添加失败:"+testwj);
				}
			}
		msg.setMsg(MSG.toString());
		System.out.println(MSG);
		return msg;

	}
}
