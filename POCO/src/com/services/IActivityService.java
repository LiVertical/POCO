package com.services;

import java.io.File;
import java.util.List;

public interface IActivityService {


	void doSaveActivity(String activityName, List<File> products,
			List<String> productIds, List<String> productNames, String userId,
			String activityDesc, String activityInfo, String createTime,
			String endTime);

}
