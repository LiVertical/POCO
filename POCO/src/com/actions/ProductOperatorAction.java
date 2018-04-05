 package com.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.util.UUIDUtil;

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
	private static final int BUFFER_SIZE = 1024 * 1024;
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
	
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 返回上传文件保存的位置
	 * 
	 * @return
	 * @throws Exception
	 */

	public String upload() {
		System.out.println("进入图片发布页面······");
		return "upload";
	}

	// 上传设置大小的方法
	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				// 每次读多少，从哪里开始，到哪里结束
				out.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String publishImages() {
		logger.info("publishImages start·····");
		try {
			HttpServletRequest request = ServletActionContext.getRequest(); 
			HttpSession session = request.getSession(); 
			productUser = session.getAttribute("loginName").toString();
			result = new JSONObject();
			if(StringUtils.isBlank(productUser)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			// 上传照片的方法
			if (image != null) {
				// 根据服务器的文件的保存地址和源文件名称创建目录文件的全部路径
				logger.info("文件名:" + image.getAbsolutePath());
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
					copy(image, file);
					// 保存路径
					url = "images" + "/" + newFileName;
					ProductInfo proInfo = new ProductInfo();
					proInfo.setProductPath(url);
				    proInfo.setUploadTime(new Date());
				    proInfo.setProductName(productName);
				    proInfo.setProductTypes(proType);
				    proInfo.setProductDesc(productDesc);
				    proInfo.setProductUser(productUser);
					productOperatorService.save(proInfo);
				}
				result.put("productName", productName);
				result.put("productType", proType);
				result.put("productPath", url);
				result.put("productDesc", productDesc);
				result.put("productUser", productUser);
				result.put("returnCode", "00");
				result.put("returnMsg", "操作成功");
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 删除
	public String deleteProductInfo() {
		logger.info("deleteProductInfo start ····");
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
		logger.info("delBatch start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(productIds)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数异常");
			return SUCCESS;
		}
		try {
			String[] proArrayStr = productIds.split(",");
			Integer[] proArray = new Integer[proArrayStr.length];
			for(int i=0;i<proArrayStr.length;i++){
				proArray[i] = Integer.parseInt(proArrayStr[i]);
			}
			if (this.productOperatorService.listProductinfo().size() > 0) {
				this.productOperatorService.delAll(proArray);
			}
			result.put("returnCode", "00");
			result.put("returnMsg", "删除成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			result.put("returnCode", "-1");
			result.put("returnMsg", "服务器异常");
		}
		return SUCCESS;
	}
		
		/**
		 * @return
		 * 作品详情页,根据userName查询作品
		 * 
		 * */
	public String queryAllProducts(){	
		result = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("loginName").toString();
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(String.valueOf(currentPage))||StringUtils.isBlank(String.valueOf(recordSize))){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			List<ProductInfo> productInfos = productOperatorService.doQueryAllProducts(recordSize,currentPage);
			result.put("productInfos", JSONArray.fromObject(productInfos, jsonConfig));
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
		 * 分类查询作品
		 * */
	public String queryProductByCondition() {
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
			e.printStackTrace();
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
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				userName = session.getAttribute("loginName").toString();
				if(StringUtils.isBlank(userName)){
					result.put("returnCode", "10");
					result.put("returnMsg", "参数错误");
				}
				List<ProductInfo> proList = productOperatorService.queryPorductByUser(userName,recordSize,currentPage);
				int totalCountPro = productOperatorService.getTotalRecordsByUser(userName);
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
		

		public File getImage() {
			return image;
		}		

		public void setImage(File image) {
			this.image = image;
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

}
