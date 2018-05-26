package com.services;

import java.util.List;

import com.entities.Work;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public interface IWorkService {

	void doAddWork(String userId, String workName, String workComment, String productGroupId, String activityId, String contestId);

	List<Work> queryProductByWorkType(Integer workType);

	int countWorks(String workName, String userName, Integer workTypeInteger);

	List<workDescInfoVo> queryWorksInfo(int currentPage, int recordSize);

	WorksInfos queryWorksInfoByWorkId(String workId);

	List<WorksInfos> queryWorks(int currentPage, int recordSize, String workName, String userName, Integer workType);

	List<WorksInfos> doQueryWorksInfoByContestId(String contestId);

	List<WorksInfos> queryWorksByActivityId(String activityId);


}
