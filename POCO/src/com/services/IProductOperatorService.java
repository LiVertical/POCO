package com.services;

import java.util.List;
import java.util.Map;

import com.entities.ProductInfo;

public interface IProductOperatorService {
	//上传作品
	void save(ProductInfo productInfo);
	
	//展示作品
	List<ProductInfo> listProductinfo();
	
	//批量删除
	void delAll(Integer[] productIds);
	
	//根据Id查询作品
	ProductInfo findById(Integer productId);
	
	List<Map> queryProductByCondition(int proType,int recordSize,int currentPage);
	
	List<ProductInfo> queryPorductByUser(String userName,int recordSize,int curPage);

	int getTotalRecords(int proType);
	
	int getTotalRecordsByUser(String userName);

	List<ProductInfo> doQueryAllProducts(int recordSize, int curPage);

	void doDeleteProductInfo(String productId);

	List<ProductInfo> doQueryProductInfosByProductId(String productId);
}
