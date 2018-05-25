package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.entities.ProductInfo;
import com.entities.Users;
import com.util.UUIDUtil;
import com.vo.ProductInfosVo;

public class ProductOperatorDao extends BaseDao {

	Logger logger = Logger.getLogger(this.getClass());

	public void doSaveProductInfo(String url, String productName, int proType,String productDesc, String productUser, String productGroupId,
			String activityId, String contestId) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(UUIDUtil.generateUUID());
		productInfo.setProductName(productName);
		productInfo.setProductPath(url);
		productInfo.setProductTypes(proType);
		productInfo.setProductDesc(productDesc);
		productInfo.setUploadTime(new Date());
		productInfo.setProductUser(productUser);
		productInfo.setProductGroupId(productGroupId);
		if(StringUtils.isNotBlank(activityId)){
			productInfo.setActivityId(activityId);
			productInfo.setProductGroupId(activityId.substring(0, 15));
		}
		if(StringUtils.isNotBlank(contestId)){
			productInfo.setContestId(contestId);
			productInfo.setProductGroupId(contestId.substring(0, 15));
		}
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
	
	public List<ProductInfosVo> queryAllProducts(int recordSize,int curPage){
		String hql = "FROM ProductInfo";
		List<ProductInfo> lists = getSession().createQuery(hql).setFirstResult((curPage-1)*recordSize).setMaxResults(recordSize).list();
		List<ProductInfosVo> productInfoList = new ArrayList<ProductInfosVo> ();
		try {
			for(ProductInfo list : lists){
				ProductInfosVo productInfosVo = new ProductInfosVo();
				String userId = list.getProductUser();
				String sql = "FROM Users WHERE userId = '" + userId + "'";
				Users user = (Users) getSession().createQuery(sql).list().get(0);
				productInfosVo.setProductId(list.getProductId());
				productInfosVo.setProductName(list.getProductName());
				productInfosVo.setProductDesc(list.getProductDesc());
				productInfosVo.setProductPath(list.getProductPath());
				productInfosVo.setProductTypes(list.getProductTypes());
				productInfosVo.setWorkId(list.getWorkId());
				productInfosVo.setUploadTime(list.getUploadTime());
				productInfosVo.setProductUserName(user.getUserName());
				productInfoList.add(productInfosVo);
			}
		} catch (HibernateException e) {
			logger.error("查询作品所属用户异常", e);
		}
		return productInfoList;
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

	public List<ProductInfo> doQueryProductsByType(int proType) {
		String hql = "FROM ProductInfo WHERE productTypes= '" + proType + "'";
		return getSession().createQuery(hql).list();
	}

	public int countProducts() {
		int size = 0;
		try {
			String hql = "FROM ProductInfo";
			size = getSession().createQuery(hql).list().size();
		} catch (HibernateException e) {
			logger.error("查询图片数目异常", e);
		}
		return size;
	}

}
