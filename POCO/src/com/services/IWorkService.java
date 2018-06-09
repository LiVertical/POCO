package com.services;

import java.util.Date;
import java.util.List;

import com.entities.Work;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public interface IWorkService {

	void doAddWork(String userId, String workName, String workComment, String productGroupId, String activityId, String contestId);

	List<Work> queryProductByWorkType(Integer workType);

	int countWorks(String workName, String userName,String userId, Integer workTypeInteger,String biginDate,String endDate);

	List<workDescInfoVo> queryWorksInfo(int currentPage, int recordSize);

	WorksInfos queryWorksInfoByWorkId(String workId);

	List<WorksInfos> queryWorks(int currentPage, int recordSize, String workName, String userName,String userId, Integer workType,String biginDate,String enDate);

	List<WorksInfos> doQueryWorksInfoByContestId(String contestId);

	List<WorksInfos> queryWorksByActivityId(String activityId);

	void deleteWorkByWorkId(String workId);

	void delWorks(String[] proArrayStr);

}
