package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.entities.ProductInfo;

public class ProductOperatorDao extends BaseDao {

	Logger logger = Logger.getLogger(this.getClass());

	public void save(ProductInfo productInfo) {
		this.getSession().save(productInfo);
	}

	public void delete(String productId) {
		String hql = "DELETE FROM ProductInfo p WHERE p.productId = ?";
		getSession().createQuery(hql).setString(0, productId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ProductInfo> getProductinfos() {
		return this.getSession().createQuery("FROM ProductInfo ORDER BY productId").list();
	}

	// 根据Id查询图片
	@SuppressWarnings("unchecked")
	public ProductInfo findById(String productId) {
		String hql = "FROM ProductInfo as p WHERE p.productId=?";
		List<ProductInfo> list = (List<ProductInfo>) getSession().createQuery(hql).setString(0, productId).list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	//根据用户查询作品
	public List<ProductInfo> queryProductByUser(String userId,int recordSize,int curPage){
		List<ProductInfo> proList = new ArrayList<ProductInfo>();
		try {
			if (recordSize == 0) {
				recordSize = 10;
			}
			if (curPage == 0) {
				curPage = 1;
			}
			String hql = "FROM ProductInfo as p WHERE p.productUser=?";
			proList = getSession().createQuery(hql).setString(0, userId).setFirstResult((curPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (Exception e) {
			logger.error("查询用户作品失败！", e);
		}
		return proList;	
	}
	
	//按类型查询
	public List<Map> queryProductByCondition(int proType, int recordSize,int currentPage) {
		List<Map> list = null;
		if ((proType <= 0 || proType > 8) && proType != 100) {
			return null;
		}
		String hql = "FROM ProductInfo";
		if (proType != 100) {
			hql += " WHERE  productTypes = '" + proType + "'";
			list = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		}else{
			list = getSession().createQuery(hql).list();
		}
		return list;
	}
	
	public int getTotalRecords(int proType){
		String hql = "FROM ProductInfo";		
		int totalRecords = 0;	
		Query query;
		List<ProductInfo> list;
		if((proType<=0 || proType>8) && proType!=100)
			return 0;
			if(proType==100){
				query = getSession().createQuery(hql);		
				list = query.list();
				totalRecords = list.size();
			}else{
				hql+=" WHERE productTypes='"+proType+"'";
				query = getSession().createQuery(hql);
				list = query.list();
				totalRecords = list.size();
			}
		logger.info("作品数量:"+totalRecords);
		return totalRecords;
	}
	
	//查询所有作品
	public int getTotalRecordsByUser(String userId){
		String hql = "FROM ProductInfo as p WHERE p.productUser='"+userId+"'";
		int totalProCount = getSession().createQuery(hql).list().size();
		return totalProCount;
	}

	// delAll
	public void delAll(String[] productIds) {
		for (int i = 0; i < productIds.length; i++) {
			ProductInfo product = this.findById(productIds[i]);
			getSession().delete(product);
		}
	}
	
	public List<ProductInfo> queryAllProducts(int recordSize,int curPage){
		if (recordSize == 0) {
			recordSize = 10;
		}
		if (curPage == 0) {
			curPage = 1;
		}
		String hql = "FROM ProductInfo";
		return getSession().createQuery(hql).setFirstResult((curPage-1)*recordSize).setMaxResults(recordSize).list();
	}

	public List<ProductInfo> queryProductInfosByProductId(String productId) {
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		try {
			String hql = "FROM ProductInfo as p WHERE p.productId='" + productId + "'";
			productInfos = getSession().createQuery(hql).list();
		} catch (Exception e) {
			logger.error("查询作品信息异常", e);
		}
		return productInfos;
	}
}
