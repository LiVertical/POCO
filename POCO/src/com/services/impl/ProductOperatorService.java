package com.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dao.ProductOperatorDao;
import com.entities.ProductInfo;
import com.services.IProductOperatorService;
import com.vo.ProductInfosVo;

public class ProductOperatorService implements IProductOperatorService{
	private ProductOperatorDao productOperatorDao;
	private ProductInfo productId;
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void saveProductInfo(String url, String productName, int proType, String productDesc, String productUser, String productGroupId,
			String activityId, String contestId) {
		productOperatorDao.doSaveProductInfo(url, productName, proType, productDesc, productUser, productGroupId, activityId, contestId);
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
	public void delAll(String[] productIds){
		productOperatorDao.delAll(productIds);
	}

	public ProductInfo findById(String productId) {		
		return productOperatorDao.findById(productId);
	}

	//����������ѯ
	public List<Map> queryProductByCondition(int proType,int recordSize,int currentPage){
		List<Map> productInfos =  productOperatorDao.queryProductByCondition(proType, recordSize, currentPage);
		logger.info("ProductOperatorService�еĶ���"+productInfos);
		return productInfos;
	}
	//���ݵ�ǰ�û�����ѯ���û�����Ʒ
	public List<ProductInfo> queryPorductByUser(String userId,int recordSize,int curPage){
		return productOperatorDao.queryProductByUser(userId,recordSize,curPage);
	}
	
	
	public int getTotalRecords(int proType){
		return productOperatorDao.getTotalRecords(proType);
	}
	
	//�����û�����ѯ��Ʒ����
	public int getTotalRecordsByUser(String userId){
		return productOperatorDao.getTotalRecordsByUser(userId);
	}
	
	@Override
	public List<ProductInfosVo> doQueryAllProducts(int recordSize,int curPage, String productName) {
		return productOperatorDao.queryAllProducts(recordSize,curPage, productName);
	}
	
	@Override
	public List<ProductInfo> doQueryProductInfosByProductId(String productId) {
		return productOperatorDao.queryProductInfosByProductId(productId);
	}
	
	@Override
	public List<ProductInfo> queryProductByType(int proType) {
		return productOperatorDao.doQueryProductsByType(proType);
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

	@Override
	public int doQueryProductCount(int recordSize, int currentPage, String productName) {
		return productOperatorDao.countProducts(recordSize, currentPage, productName );
	}

}
