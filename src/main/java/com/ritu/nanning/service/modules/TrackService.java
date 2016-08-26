package com.ritu.nanning.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Track;
import com.ritu.nanning.entity.TrackVo;
import com.ritu.nanning.repository.TrackDao;
import com.ritu.nanning.repository.TrackImplDao;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.excel.ImportMsg;
import com.ritu.nanning.utils.DateUtil;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.utils.o;

/**
 * @function 轨迹业务层
 * @author cheng.G.Y
 * @date 2016-04-03
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class TrackService extends BaseService<Track,Long>{

	private TrackDao trackDao;//JPA持久层对象

	private EntityImplDao trackImplDao;//持久层对象
	
	@Override
	protected EntityDao getEntityDao() {
		return this.trackDao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.trackDao;
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return trackImplDao;
	}
	

	@Autowired
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	@Autowired
	public void setTrackImplDao(EntityImplDao trackImplDao) {
		this.trackImplDao = trackImplDao;
	}
	
	@Override
	public void dd() {
		o.o(2222);
	}
	
	@Override
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
				Track track = new Track();
				//Excel列表检测
				//主键
				//汽车编号
				list.get(i).get(statrVal++).trim();
//				track.setCarCode(list.get(i).get(statrVal++).trim());
				
				//地址
				track.setAddress(list.get(i).get(statrVal++).trim());
				
				//经度
				track.setLat(list.get(i).get(statrVal++).trim());
				
				//维度
				track.setLng(list.get(i).get(statrVal++).trim());
				
				//时间类型检测
				//日期
				String dataTdate = list.get(i).get(statrVal++).trim();//
				try {
					o.o(i,dataTdate,(dataTdate != null && !"".equals(dataTdate)));
					if (dataTdate != null && !"".equals(dataTdate)) {
						track.setTdate(DateUtil.StringToDate(dataTdate));//日期
					}
					
				} catch (Exception e) {
					msg.FaltSaveInt++;
					MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，日期格式不正确<br>\n");
					continue;
				}
				//类型
				list.get(i).get(statrVal++).trim();
//				track.setType(list.get(i).get(statrVal++).trim());
				
				try {
					add(track);
					msg.successSaveInt++;
					System.out.println("保存成功："+track);
				} catch (Exception e) {
					msg.FaltSaveInt++;
					System.out.println("信息添加失败:"+track);
				}
			}
		msg.setMsg(MSG.toString());
		System.out.println(MSG);
		return msg;

	}
}
