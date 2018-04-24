package com.dao;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.entities.Activities;
import com.util.DateUtil;
import com.util.UUIDUtil;
import com.util.UploadFileUtil;

public class ActivityDao extends BaseDao {
	
	Logger logger = Logger.getLogger(this.getClass());


	public void saveActivity(String activityName, List<File> products, List<String> productIds,
		List<String> productNames, String userId, String activityDesc, String activityInfo, String createTime, String endTime) {
		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		try {	
			Activities activity = new Activities();
			String	groupId = "";
			for(int i = 0; i < products.size(); i++){
				// 截取后缀名
				String ext = productNames.get(i).substring(productNames.get(i).lastIndexOf(".") + 1);
				// 更改源文件的名称+时间
				String newFileName = new Date().getTime() + "." + ext;
				File dstFile = new File(ServletActionContext.getServletContext().getRealPath("/images") + "/" + newFileName);
				// 如果不存在此文件夹，则自动创建
				if (!dstFile.exists() || !dstFile.isFile()) {
					dstFile.createNewFile();
				}
				uploadFileUtil.uploadImgs(products.get(i), dstFile);
				String uuid = UUIDUtil.generateUUID();
				if(i == 0){
					groupId = uuid;
				}
				activity.setActivityId(uuid);
				activity.setActivityName(activityName);
				activity.setActivityDesc(activityDesc);
				activity.setActivityInfo(activityInfo);
				activity.setCreateTime(DateUtil.convertStringToDate(createTime));
				activity.setEndTime(DateUtil.convertStringToDate(endTime));
				activity.setActivityGroupId(groupId);
			}
		} catch (ParseException e) {
			logger.error("格式化时间异常", e);
		}catch(Exception e){
			logger.error("存储活动信息异常", e);
		}
	}


	public void saveActivity(String activityName, String userId, String activityDesc, String createTime, String endTime) {
		Activities activity = new Activities();
		try {
			activity.setActivityId(UUIDUtil.generateUUID());
			activity.setUserId(userId);
			activity.setActivityName(activityName);
			activity.setActivityDesc(activityDesc);
			activity.setEndTime(DateUtil.convertStringToDate(endTime));
			activity.setCreateTime(DateUtil.convertStringToDate(createTime));
			this.getSession().save(activity);
		} catch (ParseException e) {
			logger.error("格式化时间异常", e);
		}catch(Exception e){
			logger.error("保存活动异常", e);
		}
	}


	public void auditActivity(String activityId, int curStatus) {
		String hql = "UPDATE Activities a SET a.curStatus="+curStatus;
		getSession().createQuery(hql).executeUpdate();
	}


	public List<Activities> getAllActivities() {
		return getSession().createQuery("FROM Activities").list();
	}
	
}
