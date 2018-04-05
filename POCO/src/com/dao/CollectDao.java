package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.entities.Collects;
import com.util.UUIDUtil;

public class CollectDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void doAddToCollect(String userId, String productId) {
		Collects collects = new Collects();
		try {
			collects.setId(UUIDUtil.generateUUID());
			collects.setUserId(userId);
			collects.setProductId(productId);
			collects.setCreateTime(new Date());
			getSession().save(collects);
		} catch (Exception e) {
			logger.error("保存收藏的对象失败", e);
		}
	}

	public void deleteCollects(String productId) {
		String hql = "DELETE FROM Likes where productId='" + productId + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	public List<Collects> queryCollectsByUserId(String userId) {
		String hql = "FROM Collects where userId='" + userId + "'";
		return getSession().createQuery(hql).list();
	}

}
