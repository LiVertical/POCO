package com.dao;

import java.util.List;

import com.entities.WorkType;

public class WorkTypeDao extends BaseDao{

	public List<WorkType> findList(int currentPage, int recordSize) {
		String hql = "FORM WorkType";
		List<WorkType> list = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		return list;
	}

	public int count() {
		return 0;
	}

	public void save(WorkType workType) {
		getSession().save(workType);
	}

	public void update(WorkType workType) {
		getSession().update(workType);
	}

	public void delete(WorkType workType) {
		getSession().delete(workType);
	}

	public List<WorkType> findList(String typeValue) {
//		WorkType find = find(typeValue);
		String hql = "FORM WorkType WHERE fatherType ='"+typeValue+"'";
		return getSession().createQuery(hql).list();
	}

	public WorkType find(String typeValue) {
		String hql = "FORM WorkType WHERE typeValue ='"+typeValue+"'";
		return (WorkType) getSession().createQuery(hql).list().get(0);
	}

	public List<WorkType> findListFather() {
		String hql = "FORM WorkType WHERE fatherType is null";
		return getSession().createQuery(hql).list();
	}

}
