package com.services.impl;

import java.util.List;

import com.dao.LikeDao;
import com.entities.Likes;
import com.entities.ProductInfo;
import com.services.ILikeService;

public class LikeService implements ILikeService{
	private LikeDao likeDao;

	@Override
	public List<Likes> setTags(String userId, String productId, String productName) {
		return likeDao.doSave(userId,productId, productName);
	}
	
	//获取作品的点赞数目
	@Override
	public int doGetLikesNum(String productId) {
		return likeDao.queryLikesNum(productId);
	}
	
	@Override
	public void doCancleLike(String id) {
		likeDao.doDelete(id);
	}
	
	@Override
	public List<Likes> queryProductByUserId(String userId) {
		return likeDao.queryProductsByUserId(userId);
	}
	
	public LikeDao getLikeDao() {
		return likeDao;
	}

	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

}
