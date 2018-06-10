package com.services.impl;

import java.util.List;

import com.dao.WorkTypeDao;
import com.entities.WorkType;
import com.services.IWorkTypeService;
import com.util.Page;

public class WorkTypeService implements IWorkTypeService{
	
	private WorkTypeDao workTypeDao;

	@Override
	public Page<WorkType> findPage(int currentPage, int recordSize) {
		List<WorkType> workTpyes = workTypeDao.findList(currentPage,recordSize);
		int count = workTypeDao.count();
		return new Page<WorkType>(workTpyes, count);
	}

	@Override
	public void save(WorkType workType) {
		workTypeDao.save(workType);
	}

	@Override
	public void update(WorkType workType) {
		workTypeDao.update(workType);
	}

	@Override
	public void delete(WorkType workType) {
		workTypeDao.delete(workType);
	}

	@Override
	public List<WorkType> findList(String typeValue) {
		return workTypeDao.findList(typeValue);
	}

	@Override
	public WorkType find(String typeValue) {
		return workTypeDao.find(typeValue);
	}

	@Override
	public List<WorkType> findListFather() {
		return workTypeDao.findListFather();
	}

	public WorkTypeDao getWorkTypeDao() {
		return workTypeDao;
	}

	public void setWorkTypeDao(WorkTypeDao workTypeDao) {
		this.workTypeDao = workTypeDao;
	}

}
