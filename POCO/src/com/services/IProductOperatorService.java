package com.services;

import java.util.List;
import java.util.Map;

import com.entities.ProductInfo;
import com.vo.ProductInfosVo;

public interface IProductOperatorService {
	
	//展示作品
	List<ProductInfo> listProductinfo();
	
	//批量删除
	void delAll(String[] productIds);
	
	//根据Id查询作品
	ProductInfo findById(String productId);
	
	List<Map> queryProductByCondition(int proType,int recordSize,int currentPage);
	
	List<ProductInfo> queryPorductByUser(String userId,int recordSize,int curPage);

	int getTotalRecords(int proType);
	
	int getTotalRecordsByUser(String userId);

	List<ProductInfosVo> doQueryAllProducts(int recordSize, int curPage, String productName);

	void doDeleteProductInfo(String productId);

	List<ProductInfo> doQueryProductInfosByProductId(String productId);

	List<ProductInfo> queryProductByType(int proType);

	int doQueryProductCount(int recordSize, int currentPage, String productName);

	void saveProductInfo(String url, String productName, int proType, String productDesc, String productUser, String productGroupId,
			String activityId, String contestId);

}
