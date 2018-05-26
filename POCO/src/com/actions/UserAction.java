package com.actions;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.constants.FileConstants;
import com.entities.Users;
import com.opensymphony.xwork2.ActionSupport;
import com.services.IUserService;
import com.util.JsonDateValueProcessor;
import com.util.LoginUserUtil;
import com.util.UploadFileUtil;

public class UserAction extends ActionSupport{

	private InputStream inputStream;
	private IUserService userService;
	private String userId;
	private Users user;
	private String userName;
	private static final int BUFFER_SIZE = 1024 * 1024;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String savePath = "upload";
	private String url;
	private int currentPage;
	private int recordSize;
	private int age;
	private String email;
	private int sex;
	private String newPass;
	private String oldPass;
	private JSONObject result;
	Logger logger = Logger.getLogger(this.getClass());

	//查询用户信息
	public String queryUserDetails(){
		logger.info("UserAction.queryUserDetails start ·····");
		result = new JSONObject();
		if(StringUtils.isBlank(String.valueOf(currentPage)) || StringUtils.isBlank(String.valueOf(recordSize))){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			result.put("userInfos", JSONArray.fromObject(userService.queryUserByCondition(recordSize, currentPage,userName), jsonConfig));
			result.put("usersCount", userService.countUser());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询用户信息成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "服务器异常");
			logger.info("查询用户信息异常",e);
			e.printStackTrace();
		}	
		return SUCCESS;
	}
	
	/**
	 * @return
	 * 删除用户信息（管理员侧）
	 * */
	public String deleteUserInfo(){
		logger.info("UserAction.deleteUserInfo start ·····");
		result = new JSONObject();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			userService.doDeleteUserInfo(userId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除用户信息成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常！");
			logger.info("删除用户信息异常", e);
		}
		return SUCCESS;
	}
	
		
	/**
	 * @return
	 * 查询用户信息(用户侧)
	 * */
	public String getUserInfo(){
		logger.info("UserAction.getUserInfo start·····");
		try {
			result = new JSONObject();
			userId = LoginUserUtil.getUserInfo().getUserId();
			if(StringUtils.isBlank(userId)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
				return SUCCESS;
			}
			result.put("userInfos", userService.getUserById(userId));
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.info("查询用户信息异常！", e);
		}
		result.put("returnCode", "00");
		result.put("returnMsg", "查询用户信息成功");
		return SUCCESS;
	}
	
	/**
	 * @return
	 * 上传用户头像
	 * */
	public String uploadUserImg(){
		logger.info("UserAction.uploadUserImg start·····");
		result = new JSONObject();
		try{
			userId = LoginUserUtil.getUserInfo().getUserId();
			if(StringUtils.isBlank(upload.getName())||StringUtils.isBlank(userId)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数异常");
				return SUCCESS;
			}
			//获取后缀名
			String arrix = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			//得到新的图片名
			String newFileName = new Date().getTime()+"."+arrix;
			logger.info("新文件名：:"+newFileName);
			url = "images"+ "/"+newFileName;	
			File file = new File(FileConstants.FILE_STORE_PATH);
			//如果文件夹不存在则创建
			if(file.isDirectory() && file.exists()==false){
				file.mkdir();
	        }
			//把文件复制过去
			File img = new File(FileConstants.FILE_STORE_PATH + "/"+newFileName);
			UploadFileUtil uploadFileUtil = new UploadFileUtil();
			uploadFileUtil.uploadImgs(upload, img);
			userService.saveOrUpdateUserImg(userId, url);	
			result.put("returnCode", "00");
			result.put("returnMsg", "保存用户头像成功");
			return SUCCESS;
		}catch(Exception e){
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.info("保存用户头像异常！", e);
		}
		return SUCCESS;				
	}	
	
	/**
	 * @return
	 * 更新用户信息
	 *
	 * */
	public String saveOrUpdateUserInfo(){
		logger.info("UserAction.saveOrUpdateUserInfo start ·····");
		result = new JSONObject();
		userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(String.valueOf(sex))||StringUtils.isBlank(email)||StringUtils.isBlank(String.valueOf(age))){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数异常");
			return SUCCESS;
		}
		try {
			userService.doSaveOrUpdateUserInfo(userId, age, email, sex);
			result.put("returnCode", "00");
			result.put("returnMsg", "更新用户信息异常！");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.info("更新用户信息异常", e);
		}
		return SUCCESS;
	}
	
	//更新密码
	public String updatePass(){
		logger.info("UserAction.updatePass start······");
		result = new JSONObject();
		if(StringUtils.isBlank(newPass)||StringUtils.isBlank(oldPass)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			userId = LoginUserUtil.getUserInfo().getUserId();
			userService.doUpdatePass(userId, newPass);
			result.put("returnCode", "00");
			result.put("returnMsg", "更新密码成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部徐服务器异常");
			logger.error("更新用户密码异常！", e);
		}
		return SUCCESS;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
		
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	
}
