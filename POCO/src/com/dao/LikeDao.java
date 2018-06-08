package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.entities.Likes;
import com.entities.ProductInfo;
import com.util.UUIDUtil;

public class LikeDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public List<Likes> doSave(String userId, String productId, String productName) {
		String sql = "FROM Likes WHERE userId ='" + userId + "' AND productId = '" + productId + "'";
		List<Likes> ls = getSession().createQuery(sql).list();
		if(ls.size() < 1){
			Likes likes = new Likes();
			likes.setId(UUIDUtil.generateUUID());
			likes.setUserId(userId);
			likes.setProductId(productId);
			likes.setProductName(productName);
			likes.setCreateTime(new Date());
			this.getSession().save(likes);
		}
		return ls;
	}

	public void doDelete(String id) {
		String sql = "DELETE FROM Likes l WHERE l.productId=?";
		getSession().createQuery(sql).setString(0, id).executeUpdate();
	}

	public int queryLikesNum(String productId) {
		int likesNum = 0;
		String sql = "FROM Likes l WHERE l.productId=?";
		try {
			likesNum = getSession().createQuery(sql).setString(0, productId).list().size();
		} catch (Exception e) {
			logger.error("查询点赞数目异常", e);
		}
		return likesNum;
	}

	public List<Likes> queryProductsByUserId(String userId) {
		List<Likes> likes = new ArrayList<Likes>();
		try {
			String hql = "FROM Likes l WHERE userId = ?";
			likes = getSession().createQuery(hql).setString(0, userId).list();
		} catch (Exception e) {
			logger.error("查询用户点赞作品失败", e);
		}
		return likes;
	}


}
