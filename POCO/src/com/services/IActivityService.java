package com.services;

import java.io.File;
import java.util.List;

import com.entities.Activities;

public interface IActivityService {


	void doSaveActivity(String activityName, List<File> products,
			List<String> productIds, List<String> productNames, String userId,
			String activityDesc, String activityInfo, String createTime,
			String endTime);

	List<Activities> queryAllActivitiesByCondition(int currentPage, int pageSize);

	int queryAllActivitiesCount();

	void doApplyActivity(String userId, String activityName, String activityDesc, String createTime, String endTime);

	void doAuditActivity(String activityId, int curStatus);

}
