package com.ritu.nanning.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Track;
import com.ritu.nanning.entity.TrackVo;
import com.ritu.nanning.utils.base.EntityImplDao;

/**
 * @function 轨迹持久层
 * @author cheng.G.Y
 * @date 2016-04-03
 * @latitude 1.0
 */
@Repository
public class TrackImplDao implements EntityImplDao<TrackVo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param TrackVo (与界面交互的Track类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object trackVo2) {
		TrackVo trackVo = (TrackVo)trackVo2;
		StringBuilder hql = new StringBuilder("FROM Track AS o WHERE 1 =1 ");
		if (trackVo != null) {
		  	if(trackVo.getCarCode() != null ){
		  		hql.append("And o.carCode >= :carCode ");
		  	}if( trackVo.getCarCode_max() != null){
		  		hql.append("And o.carCode <= :carCode_max ");
		  	}
			if(trackVo.getAddress() != null && trackVo.getAddress() != "")
				hql.append("And o.address like :address ");
			if(trackVo.getLat() != null && trackVo.getLat() != "")
				hql.append("And o.lat like :lat ");
			if(trackVo.getLng() != null && trackVo.getLng() != "")
				hql.append("And o.lng like :lng ");
		  	if(trackVo.getTdate() != null ){
		  		hql.append("And o.tdate >= :tdate ");
		  	}if( trackVo.getTdate_max() != null){
		  		hql.append("And o.tdate <= :tdate_max ");
		  	}
		  	if(trackVo.getType() != null ){
		  		hql.append("And o.type >= :type ");
		  	}if( trackVo.getType_max() != null){
		  		hql.append("And o.type <= :type_max ");
		  	}
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param TrackVo (与界面交互的Track类)
	 */
	public void setParameters(Query query, Object trackVo2) {
		TrackVo trackVo = (TrackVo)trackVo2;
		if (trackVo != null) {
		  	if(trackVo.getCarCode() != null){
		  		query.setParameter("carCode", trackVo.getCarCode());
		  	}if( trackVo.getCarCode_max() != null){
				query.setParameter("carCode_max", trackVo.getCarCode_max());
		  	}
			if(trackVo.getAddress() != null && trackVo.getAddress() != "")
				query.setParameter("address", "%" + trackVo.getAddress() + "%");
			if(trackVo.getLat() != null && trackVo.getLat() != "")
				query.setParameter("lat", "%" + trackVo.getLat() + "%");
			if(trackVo.getLng() != null && trackVo.getLng() != "")
				query.setParameter("lng", "%" + trackVo.getLng() + "%");
		  	if(trackVo.getTdate() != null){
		  		query.setParameter("tdate", trackVo.getTdate());
		  	}if( trackVo.getTdate_max() != null){
				query.setParameter("tdate_max", trackVo.getTdate_max());
		  	}
		  	if(trackVo.getType() != null){
		  		query.setParameter("type", trackVo.getType());
		  	}if( trackVo.getType_max() != null){
				query.setParameter("type_max", trackVo.getType_max());
		  	}
		}
	}
	
}
