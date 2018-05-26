package com.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;

import com.entities.ProductInfo;
import com.entities.Users;
import com.entities.Work;
import com.util.MyComparator;
import com.util.UUIDUtil;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public class WorkDao extends BaseDao{

	Logger logger = Logger.getLogger(this.getClass());
	
	public void addWork(String userId, String workName, String workComment, String productGroupId, String activityId, String contestId) {
		try {
			Work work = new Work();
			work.setWorkId(UUIDUtil.generateUUID());
			work.setWorkName(workName);
			work.setWorkComment(workComment);
			work.setProductGroupId(productGroupId);
			work.setUserId(userId);
			work.setWorkUploadTime(new Date());
			if(StringUtils.isNotBlank(activityId)){
				work.setActivityId(activityId);
				work.setProductGroupId(activityId.substring(0, 15));
			}else{
				work.setActivityId("活动id");
			}
			if(StringUtils.isBlank(contestId)){
				work.setContestId("大赛id");
			}else{
				work.setContestId(contestId);
				work.setProductGroupId(contestId.substring(0, 15));
			}
			getSession().save(work);
		} catch (Exception e) {
			logger.error("保存作品异常", e);
		}
	}

	public List<Work> getWorksByWorkType(Integer workType) {
		
		String sql = "FROM Work WHERE 1=1";
		if (workType != null) {
			sql = sql +"workType = '"+ workType +"'";
		}
		return getSession().createQuery(sql).list();
	}

	public List<WorksInfos> doQueryAllWorksInfo(int currentPage, int recordSize, String workName, String userName, Integer workType) {
		List<WorksInfos> workInfos = new ArrayList<WorksInfos>();
		ArrayList<Users> users = new ArrayList<Users>();
//		String sqll = "FROM Work ";
//		String sql = "FROM Work w,User WHERE workType = :workType";
//		if (workName != null && "".equals(workName)) {
//			sql = sql + "AND workName LIKE :workNname";
//		}
//		if (userName != null && "".equals(userName)) {
//			String userSql = "FORM Users WHERE userName LIKE :userName";
//			users = (ArrayList<Users>) getSession().createQuery(userSql).setString("userName", "%"+userName+"%").list();
//			sql = sql + "AND userId in {}";
//		}
		
		
		String sql = "SELECT w.work_id,w.work_name,w.work_comment,w.work_upload_time,w.product_group_id,"
				+ "us.userName FROM `work` AS w,`users` AS us WHERE w.user_id=us.userId ";
		if (workType != null) {
			sql = sql +"AND w.work_type="+workType;
		}
		if (userName != null && !"".equals(userName)) {
			sql = sql + "AND us.userName like '%"+userName+"%'";
		}
		if (workName != null && !"".equals(workName)) {
			sql = sql +"AND w.work_name like '%"+workName+"%'";
		}
		
		List list = getSession().createSQLQuery(sql)
		.addScalar("w.work_id",StandardBasicTypes.STRING)
		.addScalar("w.work_name",StandardBasicTypes.STRING)
		.addScalar("w.work_comment",StandardBasicTypes.STRING)
		.addScalar("w.work_upload_time",StandardBasicTypes.DATE)
		.addScalar("w.product_group_id",StandardBasicTypes.STRING)
		.addScalar("us.userName",StandardBasicTypes.STRING)
		.setFirstResult((currentPage-1)*recordSize)
		.setMaxResults(recordSize).list();
		
		for (Object obj : list) {
			Object[] objs = (Object[]) obj;
			String workIdStr = (String) objs[0];
			String workNameStr = (String) objs[1];
			String workCommentStr = (String) objs[2];
			Date workUploadTime = (Date) objs[3];
			String productGroupIdStr = (String) objs[4];
			String userNameStr = (String) objs[5];
			if (productGroupIdStr != null && !"".equals(productGroupIdStr)) {
				WorksInfos workVo = new WorksInfos();
				String sql2 = "FROM ProductInfo WHERE productGroupId = '" + productGroupIdStr + "'";
				List<ProductInfo> products = getSession().createQuery(sql2).list();
				workVo.setProductInfos(products);
				workVo.setUserName(userNameStr);
				workVo.setWorkId(workIdStr);
				workVo.setWorkName(workNameStr);
				workVo.setWorkComment(workCommentStr);
				workVo.setWorkUploadTime(workUploadTime.toString());
				workInfos.add(workVo);
			}
			
		}
				
//				.setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
//		for(Object[] work : works){
//			if(work.getProductGroupId() != null){
//				
//				String sql2 = "FROM ProductInfo WHERE productGroupId = '" + work.getProductGroupId() + "'";
//				List<ProductInfo> products = getSession().createQuery(sql2).list();
//				Users user = new Users();
//				String sql3 = "FROM Users WHERE userId = '"+work.getUserId()+"'";
//				user = (Users) getSession().createQuery(sql3).list().get(0);
//				workVo.setProductInfos(products);
//				workVo.setUserName(user.getUserName());
//				workVo.setWorkId(work.getWorkId());
//				workVo.setWorkName(work.getWorkName());
//				workVo.setWorkComment(work.getWorkComment());
//				workVo.setWorkUploadTime(work.getWorkUploadTime().toString());
//				workInfos.add(workVo);
//			}
//		}
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

	public List<WorksInfos> queryWorkInfosByContestId(String contestId) {
		List<WorksInfos> workInfos = new ArrayList<WorksInfos>();
		try {
			String sql1 = "FROM Work WHERE contestId='"+contestId+"'";
			List<Work> work = new ArrayList<Work>();
			work = getSession().createQuery(sql1).list();
			for(Work info: work){
				WorksInfos infos = new WorksInfos();
				int voteNum = 0;
				String productGroupId = info.getProductGroupId();
				String sql2 = "FROM ProductInfo WHERE productGroupId='"+productGroupId+"'";
				List<ProductInfo> products = getSession().createQuery(sql2).list();
				String sql3 = "FROM Vote WHERE workId='"+info.getWorkId()+"'";
				voteNum = getSession().createQuery(sql3).list().size();
				infos.setProductInfos(products);
				infos.setVoteNum(voteNum);
				infos.setWorkName(info.getWorkName());
				infos.setUserId(info.getUserId());
				infos.setWorkComment(info.getWorkComment());
				infos.setWorkId(info.getWorkId());
				workInfos.add(infos);
			}
		} catch (Exception e) {
			logger.error("查询大赛作品异常", e);
		}
		Collections.sort(workInfos);
		//必须是Comparator中的compare方法和Collections.sort方法配合使用才管用  
		 MyComparator mc = new MyComparator() ;  
		 Collections.sort(workInfos, mc) ;
		 return workInfos;
	}

}
