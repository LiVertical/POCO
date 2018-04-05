package com.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import com.dao.CollectDao;
import com.entities.Collects;
import com.entities.ProductInfo;
import com.services.ICollectService;
import com.util.JsonDateValueProcessor;
import com.vo.CollectsVo;

public class CollectService implements ICollectService{
	
	Logger logger = Logger.getLogger(this.getClass());

	private CollectDao collectDao; 

	@Override
	public void doAddCollect(String userId, String productId) {
		collectDao.doAddToCollect(userId, productId);
	}
	
	@Override
	public void doDelelteCollect(String productId) {
		collectDao.deleteCollects(productId);
	}
	
	@Override
	public List<Collects> doQueryCollectByUserId(String userId) {
		List<Collects> collects = new ArrayList<Collects>();
		collects = collectDao.queryCollectsByUserId(userId);
		return collects;
	}

	public CollectDao getCollectDao() {
		return collectDao;
	}

	public void setCollectDao(CollectDao collectDao) {
		this.collectDao = collectDao;
	}

}
