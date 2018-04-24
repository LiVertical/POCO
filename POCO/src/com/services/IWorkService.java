package com.services;

import java.util.List;

import com.entities.Work;

public interface IWorkService {

	void doAddWork(String userId, String workName, String workComment, String productGroupId);

	List<Work> queryProductByWorkType(int workType);

}
