package com.dao;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.constants.ActivityConstants;
import com.entities.Activities;
import com.entities.Users;
import com.util.DateUtil;
import com.util.UUIDUtil;
import com.util.UploadFileUtil;
import com.vo.ActivityInfoVo;

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
			activity.setApplyTime(new Date());
			activity.setCurStatus(0);
			activity.setAuditStatus(0);
			this.getSession().save(activity);
		} catch (ParseException e) {
			logger.error("格式化时间异常", e);
		}catch(Exception e){
			logger.error("保存活动异常", e);
		}
	}


	public void auditActivity(String activityId, int curStatus) {
		int status = ActivityConstants.ACTIVITY_STATUS_NOT_PASSED;
		if(curStatus == ActivityConstants.ACTIVITY_STATUS_PASSED){
			status = ActivityConstants.ACTIVITY_STATUS_PASSED;
		}
		String hql = "UPDATE Activities SET curStatus="+status+", auditStatus=" + ActivityConstants.ACTIVITY_AUDIT_STATUS_ALREADY + "WHERE activityId ='"+activityId+"'";
		getSession().createQuery(hql).executeUpdate();
	}


	public List<Activities> getAllActivities() {
		return getSession().createQuery("FROM Activities ORDER BY createTime DESC").list();
	}


	public Activities queryActivityInfos(String activityId) {
		return (Activities) getSession().createQuery("FROM Activities").list().get(0);
	}


	public List<ActivityInfoVo> doQueryAllActivities(int currentPage,int recordSize,Integer auditStatus) {
		List<ActivityInfoVo> activitysInfoVo = new ArrayList<ActivityInfoVo>();
		List<Activities> activities = new ArrayList<Activities>();
		String sql = "FROM Activities WHERE auditStatus="+auditStatus;
		activities = getSession().createQuery(sql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		for(Activities  activityInfo : activities){
			ActivityInfoVo activityInfoVo = new ActivityInfoVo();
			String sql2 = "FROM Users WHERE userId = '" + activityInfo.getUserId() + "'";
			List<Users> users = new ArrayList<Users>();
			users = getSession().createQuery(sql2).list();
			Users user = new Users();
			if(users.size() > 0){
				user = users.get(0);
				activityInfoVo.setActivityUser(user.getUserName());
			}
			activityInfoVo.setActivityId(activityInfo.getActivityId());
			activityInfoVo.setActivityName(activityInfo.getActivityName());
			activityInfoVo.setActivityDesc(activityInfo.getActivityDesc());
			activityInfoVo.setAuditStatus(activityInfo.getAuditStatus());
			activityInfoVo.setApplyTime(activityInfo.getApplyTime());
			activityInfoVo.setStartTime(activityInfo.getCreateTime());
			activityInfoVo.setCurStatus(activityInfo.getCurStatus());
			activityInfoVo.setEndTime(activityInfo.getEndTime());
			activitysInfoVo.add(activityInfoVo);
		}
		return activitysInfoVo;
	}


	public int doQueryAllActivitiesCount() {
		int size = 0;
		size = getSession().createQuery("FROM Activities").list().size();
		return size;
	}


	public Activities queryActivityById(String activityId) {
		return (Activities) getSession().createQuery("FROM Activities WHERE activityId=:activityId").setString("activityId", activityId).list().get(0);
	}
	
}
