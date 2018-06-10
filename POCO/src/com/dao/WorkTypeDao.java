package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.entities.WorkType;

public class WorkTypeDao extends BaseDao{

	public List<WorkType> findList(int currentPage, int recordSize) {
		List<WorkType> list = new ArrayList<WorkType>();
		try {
			String hql = "FROM WorkType";
			list = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
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
		String hql = "FROM WorkType WHERE fatherType ='"+typeValue+"'";
		return getSession().createQuery(hql).list();
	}

	public WorkType find(String typeValue) {
		String hql = "FROM WorkType WHERE typeValue ='"+typeValue+"'";
		List<WorkType> workTypes =  getSession().createQuery(hql).list();
		WorkType workType = null;
		if(workTypes.size() > 0){
			workType = workTypes.get(0);
		}
		return workType;
	}

	public List<WorkType> findListFather() {
		String hql = "FROM WorkType WHERE fatherType is null";
		return getSession().createQuery(hql).list();
	}

}
