package com.services;

import java.io.File;
import java.util.List;

import com.entities.Activities;
import com.vo.ActivityInfoVo;

public interface IActivityService {


	void doSaveActivity(String activityName, List<File> products,
			List<String> productIds, List<String> productNames, String userId,
			String activityDesc, String activityInfo, String createTime,
			String endTime);

	List<ActivityInfoVo> queryAllActivitiesByCondition(int currentPage,int recordSize, Integer auditStatus);

	int queryAllActivitiesCount();

	void doApplyActivity(String userId, String activityName, String activityDesc, String createTime, String endTime);

	void doAuditActivity(String activityId, int curStatus);

	List<Activities> queryAllActivities();

	Activities doQueryActivityInfo(String activityId);

}
