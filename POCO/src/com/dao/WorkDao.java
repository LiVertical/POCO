package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.entities.ProductInfo;
import com.entities.Users;
import com.entities.Work;
import com.util.UUIDUtil;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public class WorkDao extends BaseDao{

	Logger logger = Logger.getLogger(this.getClass());
	
	public void addWork(String userId, String workName, String workComment, String productGroupId, String activityId) {
		try {
			Work work = new Work();
			work.setWorkId(UUIDUtil.generateUUID());
			work.setWorkName(workName);
			work.setWorkComment(workComment);
			work.setProductGroupId(productGroupId);
			work.setUserId(userId);
			work.setWorkUploadTime(new Date());
			work.setActivityId(activityId);
			getSession().save(work);
		} catch (Exception e) {
			logger.error("保存作品异常", e);
		}
	}

	public List<Work> getWorksByWorkType(int workType) {
		String sql = "FROM Work WHERE workType = '"+ workType +"'";
		return getSession().createQuery(sql).list();
	}

	public List<WorksInfos> doQueryAllWorksInfo(int currentPage, int recordSize) {
		List<WorksInfos> workInfos = new ArrayList<WorksInfos>();
		String sql = "FROM Work";
		List<Work> works = getSession().createQuery(sql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		for(Work work : works){
			if(work.getProductGroupId() != null){
				WorksInfos workVo = new WorksInfos();
				String sql2 = "FROM ProductInfo WHERE productGroupId = '" + work.getProductGroupId() + "'";
				List<ProductInfo> products = getSession().createQuery(sql2).list();
				Users user = new Users();
				String sql3 = "FROM Users WHERE userId = '"+work.getUserId()+"'";
				user = (Users) getSession().createQuery(sql3).list().get(0);
				workVo.setProductInfos(products);
				workVo.setUserName(user.getUserName());
				workVo.setWorkId(work.getWorkId());
				workVo.setWorkName(work.getWorkName());
				workVo.setWorkComment(work.getWorkComment());
				workVo.setWorkUploadTime(work.getWorkUploadTime().toString());
				workInfos.add(workVo);
			}
		}
		return workInfos;
	}

	public int doCountWorks() {
		String sql = "FROM Work";
		int size = 0;
		size = getSession().createQuery(sql).list().size();
		return size;
	}

	public List<workDescInfoVo> queryAllWorksInfo(int currentPage,int recordSize) {
		List<workDescInfoVo> workInfos = new ArrayList<workDescInfoVo>();
		String sql = "FROM Work";
		List<Work> works = getSession().createQuery(sql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		for(Work work : works){
			if(work.getProductGroupId() != null){
				workDescInfoVo workVo = new workDescInfoVo();
				String hql = "FROM ProductInfo WHERE productGroupId = '" + work.getProductGroupId() + "'";
				ProductInfo productInfo = (ProductInfo) getSession().createQuery(hql).list().get(0);
				workVo.setProductPath(productInfo.getProductPath());
				workVo.setUploadTime(work.getWorkUploadTime().toString());
				workVo.setWorkId(work.getWorkId());
				workVo.setWorkName(work.getWorkName());
				workInfos.add(workVo);
			}
		}
		return workInfos;
	}

	public WorksInfos queryWorkInfosByWorkId(String workId) {
		WorksInfos worksInfos = new WorksInfos();
		String sql = "FROM Work WHERE workId = '"+workId+"'";
		Work work = (Work) getSession().createQuery(sql).list().get(0);
		String sql2 = "FROM ProductInfo WHERE productGroupId = '"+work.getProductGroupId()+"'";
		Users user = new Users();
		String sql3 = "FROM Users WHERE userId = '"+work.getUserId()+"'";
		user = (Users) getSession().createQuery(sql3).list().get(0);
		List<ProductInfo> products = new ArrayList<ProductInfo>();
		products = getSession().createQuery(sql2).list();
		worksInfos.setProductInfos(products);
		worksInfos.setWorkName(work.getWorkName());
		worksInfos.setUserId(work.getUserId());
		worksInfos.setWorkUploadTime(work.getWorkUploadTime().toString());
		worksInfos.setWorkComment(work.getWorkComment());
		worksInfos.setUserName(user.getUserName());
		return worksInfos;
	}

}
