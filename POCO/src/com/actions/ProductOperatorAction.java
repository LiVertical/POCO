 package com.actions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.ProductInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.services.IProductOperatorService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;
import com.util.UUIDUtil;
import com.util.UploadFileUtil;

/**
 * 使用List上传多个文件
 * 
 * @author
 * 
 */
public class ProductOperatorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private File image; // 上传的文件
	private String imageFileName; // 上传的文件名
	private String imageContentType; // 文件类型
	private String savePath;
	private String url;
	private ProductInfo productInfo;
	private Date uploadTime;
	private String productPath;
	private String productDesc;
	private String productName;
	private String productUser;
	private IProductOperatorService productOperatorService;
	private String productId;
	private InputStream inputStream;
	private String productIds;
	private List<ProductInfo> productInfos;
	private int proType;
	private int currentPage;
	private int recordSize;
	private String condition;
	private int totalRecords;
	private JSONObject result;
	private UUIDUtil uuidUtil;
	private String userName;
	private String productGroupId;
	private String activityId;
	
	Logger logger = Logger.getLogger(this.getClass());

	public String publishImages() {
		logger.info("ProductOperatorAction.publishImages start·····");
		result = new JSONObject();
		try {
			productUser = LoginUserUtil.getUserInfo().getUserId();
			if(StringUtils.isBlank(productUser)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			// 上传照片的方法
			if (image != null) {
				// 截取后缀名
				String ext = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
				// 更改源文件的名称+时间
				String newFileName = new Date().getTime() + "." + ext;
				File file = new File(ServletActionContext.getServletContext().getRealPath("/images") + "/" + newFileName);
				logger.info("file:" + image);
				// 如果不存在此文件夹，则自动创建
				if (!file.exists() || !file.isFile()) {
					file.createNewFile();
				}
				UploadFileUtil uploadFileUtil = new UploadFileUtil();
				uploadFileUtil.uploadImgs(image, file);
				// 保存路径
				url = "images" + "/" + newFileName;
				ProductInfo proInfo = new ProductInfo();
				proInfo.setProductPath(url);
				proInfo.setUploadTime(new Date());
				proInfo.setProductName(productName);
				proInfo.setProductTypes(proType);
				proInfo.setProductDesc(productDesc);
				proInfo.setProductUser(productUser);
				proInfo.setProductGroupId(productGroupId);
				proInfo.setActivityId(activityId);
				productOperatorService.save(proInfo);
			}
			result.put("returnCode", "00");
			result.put("returnMsg", "操作成功");
		} catch (IOException e) {
			logger.error("图片上传IO流异常");
		}catch(Exception e){
			logger.error("图片上传异常", e);
		}
		return SUCCESS;
	}

	// 删除
	public String deleteProductInfo() {
		logger.info("ProductOperatorAction.deleteProductInfo start ····");
		result = new JSONObject();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String userName = session.getAttribute("loginName").toString();
			if(StringUtils.isBlank(userName)||StringUtils.isBlank(productId)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			productOperatorService.doDeleteProductInfo(productId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除成功");
		} catch (Exception e) {
			result.put("returnCode", "00");
			result.put("returnMsg", "服务器异常");
			logger.info("删除作品失败", e);
		}
		
		return SUCCESS;
	}

	// 批量删除
	public String delBatch() {
		logger.info("ProductOperatorAction.delBatch start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(productIds)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数异常");
			return SUCCESS;
		}
		try {
			String[] proArrayStr = productIds.split(",");
			if (this.productOperatorService.listProductinfo().size() > 0) {
				this.productOperatorService.delAll(proArrayStr);
			}
			result.put("returnCode", "00");
			result.put("returnMsg", "删除成功");
		} catch(Exception e){
			logger.error("删除图片异常" , e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "服务器异常");
		}
		return SUCCESS;
	}
		
		/**
		 * @return
		 * 查询所有图片（管理员侧）
		 * 
		 * */
	public String queryAllProducts(){	
		logger.info("ProductOperatorAction.queryAllProducts start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(String.valueOf(currentPage))||StringUtils.isBlank(String.valueOf(recordSize))){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("productInfos", JSONArray.fromObject(productOperatorService.doQueryAllProducts(recordSize,currentPage), jsonConfig));
			result.put("productsCount",productOperatorService.doQueryProductCount()); 
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "服务器异常");
			logger.info("查询作品信息异常", e);
		}
		return SUCCESS;
	}

		/**
		 * @return
		 * 分类分页查询作品
		 * */
	public String queryProductByCondition() {
		logger.info("ProductOperatorAction.queryProductByCondition start·····");
		try {
			result = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			@SuppressWarnings("rawtypes")
			List<Map> list = productOperatorService.queryProductByCondition(proType, recordSize, currentPage);
			JSONArray resultArray = JSONArray.fromObject(list, jsonConfig);
			result.put("productInfos", resultArray);
			totalRecords = productOperatorService.getTotalRecords(proType);
			logger.info("总记录数：" + totalRecords);
			result.put("total", totalRecords);
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("分类分页查询作品失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	public String queryProductByType() {
		logger.info("ProductOperatorAction.queryProductByType start·····");
		try {
			result = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("products", JSONArray.fromObject(productOperatorService.queryProductByType(proType), jsonConfig));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			logger.error("分类分页查询作品失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	
		/**
		 * @return
		 * 根据当前用户，查询该用户的所有作品
		 * */
		public String queryProductByUser(){
			logger.info("ProductOperatorAction.queryProductByUser start ....");
			try {
				result = new JSONObject();
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				String userId = LoginUserUtil.getUserInfo().getUserId();
				if(StringUtils.isBlank(userId)){  
					result.put("returnCode", "10");
					result.put("returnMsg", "参数错误");
				}
				List<ProductInfo> proList = productOperatorService.queryPorductByUser(userId,recordSize,currentPage);
				int totalCountPro = productOperatorService.getTotalRecordsByUser(userId);
				JSONArray productInfos = JSONArray.fromObject(proList, jsonConfig);
				result.put("productInfos", productInfos);
				result.put("totals", totalCountPro);
				result.put("returnCode", "00");
				result.put("returnMsg", "根据用户名查询作品成功");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("returnCode", "-1");
				result.put("returnMsg", "根据用户名查询作品失败,内部服务器异常");
			}
			return SUCCESS;
		}

		//根据productId查询作品信息
		public String queryPorductInfosByProductId(){
			logger.info("ProductOperatorAction.queryPorductInfosByProductId start·····");
			result = new JSONObject();
			if(StringUtils.isBlank(productId)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			try {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				result.put("productInfos", JSONArray.fromObject(productOperatorService.doQueryProductInfosByProductId(productId), jsonConfig));
				result.put("returnCode", "00");
				result.put("returnMsg", "查询作品信息成功");
			} catch (Exception e) {
				logger.error("查询作品信息失败", e);
				result.put("returnCode", "-1");
				result.put("returnMsg", "内部服务器异常");
			}
			return SUCCESS;
		}
		
		public JSONObject getResult() {
			return result;
		}

		public void setResult(JSONObject result) {
			this.result = result;
		}

		public int getTotalRecords() {
			return totalRecords;
		}

		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getRecordSize() {
			return recordSize;
		}

		public void setRecordSize(int recordSize) {
			this.recordSize = recordSize;
		}

		public List<ProductInfo> getProductInfos() {
			return productInfos;
		}

		public void setProductInfos(List<ProductInfo> productInfos) {
			this.productInfos = productInfos;
		}

		public String getProductIds() {
			return productIds;
		}

		public void setProductIds(String productIds) {
			this.productIds = productIds;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public Date getUploadTime() {
			return uploadTime;
		}

		public void setUploadTime(Date uploadTime) {
			this.uploadTime = uploadTime;
		}



		public IProductOperatorService getProductOperatorService() {
			return productOperatorService;
		}

		public void setProductOperatorService(
				IProductOperatorService productOperatorService) {
			this.productOperatorService = productOperatorService;
		}

		public ProductInfo getProductInfo() {
			return productInfo;
		}

		public void setProductInfo(ProductInfo productInfo) {
			this.productInfo = productInfo;
		}

		public String getSavePath() {
			return savePath;
		}

		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}

		public String getImageFileName() {
			return imageFileName;
		}

		public void setImageFileName(String imageFileName) {
			this.imageFileName = imageFileName;
		}

		public String getImageContentType() {
			return imageContentType;
		}

		public void setImageContentType(String imageContentType) {
			this.imageContentType = imageContentType;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getProductPath() {
			return productPath;
		}

		public void setProductPath(String productPath) {
			this.productPath = productPath;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public UUIDUtil getUuidUtil() {
			return uuidUtil;
		}

		public void setUuidUtil(UUIDUtil uuidUtil) {
			this.uuidUtil = uuidUtil;
		}

		public String getProductDesc() {
			return productDesc;
		}

		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}

		public String getProductUser() {
			return productUser;
		}

		public void setProductUser(String productUser) {
			this.productUser = productUser;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public int getProType() {
			return proType;
		}

		public void setProType(int proType) {
			this.proType = proType;
		}

		public String getProductGroupId() {
			return productGroupId;
		}

		public void setProductGroupId(String productGroupId) {
			this.productGroupId = productGroupId;
		}

		public File getImage() {
			return image;
		}

		public void setImage(File image) {
			this.image = image;
		}

		public String getActivityId() {
			return activityId;
		}

		public void setActivityId(String activityId) {
			this.activityId = activityId;
		}
		
}
