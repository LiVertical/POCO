package com.services;

import java.util.List;

import com.entities.Work;
import com.vo.WorksInfos;
import com.vo.workDescInfoVo;

public interface IWorkService {

	void doAddWork(String userId, String workName, String workComment, String productGroupId, String activityId, String contestId);

	List<Work> queryProductByWorkType(int workType);

	int countWorks();

	List<workDescInfoVo> queryWorksInfo(int currentPage, int recordSize);

	WorksInfos queryWorksInfoByWorkId(String workId);

	List<WorksInfos> queryWorks(int currentPage, int recordSize);

	List<WorksInfos> doQueryWorksInfoByContestId(String contestId);

}
