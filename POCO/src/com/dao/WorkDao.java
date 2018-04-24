package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.entities.Work;
import com.util.UUIDUtil;

public class WorkDao extends BaseDao{

	Logger logger = Logger.getLogger(this.getClass());
	
	public void addWork(String userId, String workName, String workComment, String productGroupId) {
		try {
			Work work = new Work();
			work.setWorkId(UUIDUtil.generateUUID());
			work.setWorkName(workName);
			work.setWorkComment(workComment);
			work.setProductGroupId(productGroupId);
			work.setUserId(userId);
			work.setWorkUploadTime(new Date());
			getSession().save(work);
		} catch (Exception e) {
			logger.error("保存作品异常", e);
		}
	}

	public List<Work> getWorksByWorkType(int workType) {
		String sql = "FROM Work WHERE workType = '"+ workType +"'";
		return getSession().createQuery(sql).list();
	}

}
