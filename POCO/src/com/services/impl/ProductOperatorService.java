package com.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dao.ProductOperatorDao;
import com.entities.ProductInfo;
import com.services.IProductOperatorService;

public class ProductOperatorService implements IProductOperatorService{
	private ProductOperatorDao productOperatorDao;
	private ProductInfo productId;
	Logger logger = Logger.getLogger(this.getClass());

	//save
	public void save(ProductInfo productInfo){
		this.productOperatorDao.save(productInfo);
	}
		
	//delete
	@Override
	public void doDeleteProductInfo(String productId) {
		productOperatorDao.delete(productId);
	}
	
	public List<ProductInfo> listProductinfo(){
		return this.productOperatorDao.getProductinfos();
	} 
	
	//delAll
	public void delAll(Integer[] productIds){
		productOperatorDao.delAll(productIds);
	}

	public ProductInfo findById(Integer productId) {		
		return productOperatorDao.findById(productId);
	}

	//根据条件查询
	public List<Map> queryProductByCondition(int proType,int recordSize,int currentPage){
		List<Map> productInfos =  productOperatorDao.queryProductByCondition(proType, recordSize, currentPage);
		logger.info("ProductOperatorService中的对象"+productInfos);
		return productInfos;
	}
	//根据当前用户名查询该用户的作品
	public List<ProductInfo> queryPorductByUser(String userId,int recordSize,int curPage){
		return productOperatorDao.queryProductByUser(userId,recordSize,curPage);
	}
	
	
	public int getTotalRecords(int proType){
		return productOperatorDao.getTotalRecords(proType);
	}
	
	//根据用户名查询作品数量
	public int getTotalRecordsByUser(String userId){
		return productOperatorDao.getTotalRecordsByUser(userId);
	}
	
	@Override
	public List<ProductInfo> doQueryAllProducts(int recordSize,int curPage) {
		return productOperatorDao.queryAllProducts(recordSize,curPage);
	}
	
	@Override
	public List<ProductInfo> doQueryProductInfosByProductId(String productId) {
		return productOperatorDao.queryProductInfosByProductId(productId);
	}
	
	public ProductInfo getProductId() {
		return productId;
	}
	public void setProductId(ProductInfo productId) {
		this.productId = productId;
	}

	public ProductOperatorDao getproductOperatorDao() {
		return productOperatorDao;
	}

	public void setproductOperatorDao(ProductOperatorDao productOperatorDao) {
		this.productOperatorDao = productOperatorDao;
	}

}
