package com.services;

import java.util.List;

import com.entities.WorkType;
import com.util.Page;

public interface IWorkTypeService {

	Page<WorkType> findPage(int currentPage, int recordSize);

	void save(WorkType workType);

	void update(WorkType workType);

	void delete(WorkType workType);

	List<WorkType> findList(String typeValue);

	WorkType find(String typeValue);

	List<WorkType> findListFather();

}
