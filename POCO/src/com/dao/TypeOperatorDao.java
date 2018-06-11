package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.entities.Types;

public class TypeOperatorDao extends BaseDao{

	public List<Types> findList(int currentPage, int recordSize) {
		List<Types> list = new ArrayList<Types>();
		try {
			String hql = "FROM Types";
			list = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int count() {
		return 0;
	}

	public List<Types> findList(String typeValue) {
		String hql = "FROM Types WHERE fatherType ='"+typeValue+"'";
		return getSession().createQuery(hql).list();
	}

	public Types find(String typeValue) {
		String hql = "FROM Types WHERE typeValue ='"+typeValue+"'";
		List<Types> workTypes =  getSession().createQuery(hql).list();
		Types workType = null;
		if(workTypes.size() > 0){
			workType = workTypes.get(0);
		}
		return workType;
	}

	public List<Types> findListFather() {
		String hql = "FROM Types WHERE fatherType = ''";
		return getSession().createQuery(hql).list();
	}

	public void doSaveNewWorkType(String typeName, String typeValue, String fatherType, int typeOrder) {
		Types workType = new  Types();
		workType.setTypeName(typeName);
		workType.setTypeValue(typeValue);
		workType.setFatherType(fatherType);
		workType.setTypeOrder(typeOrder);
		workType.setCreateTime(new Date());
		this.getSession().save(workType);
	}

	public void doDeleteByTypeId(String typeId) {
		String sql = "DELETE FROM Types WHERE typeId='" + typeId + "'";
		this.getSession().createQuery(sql).executeUpdate();
	}

	public List<Types> findFather(String typeId) {
		String sql = "FROM Types WHERE typeId = '" + typeId + "' AND fatherType = ' '";
		return getSession().createQuery(sql).list();
	}

	public void doDeleteFatherAndSon(String typeId) {
		String sql = "DELETE FROM Types WHERE fatherType = '" + typeId + "' OR typeId = '" + typeId + "'";
		getSession().createQuery(sql).executeUpdate();
	}

	public void updateTypeInfosByTypeId(String typeId, String typeName, String typeValue, int typeOrder, String fatherType) {
		Types typeInfo = new Types();
		typeInfo.setUpdateTime(new Date());
		typeInfo.setFatherType(fatherType);
		typeInfo.setTypeName(typeName);
		typeInfo.setTypeValue(typeValue);
		typeInfo.setTypeOrder(typeOrder);
		getSession().update(typeInfo);
	}

	public Types queryTypeInfoByTypeId(String typeId) {
		String sql = "FROM Types WHERE typeId = '" + typeId + "'";
		List<Types> types = getSession().createQuery(sql).list();
		Types type = new Types();
		if(types.size() > 0){
			type = types.get(0);
		}
		return type;
	}

}
