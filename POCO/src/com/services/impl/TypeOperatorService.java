package com.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.dao.TypeOperatorDao;
import com.entities.Types;
import com.services.ITypeOperatorService;
import com.util.Page;

public class TypeOperatorService implements ITypeOperatorService{
	
	private TypeOperatorDao typeOperatorDao;

	@Override
	public Page<Types> findPage(int currentPage, int recordSize) {
		List<Types> workTpyes = typeOperatorDao.findList(currentPage,recordSize);
		int count = typeOperatorDao.count();
		return new Page<Types>(workTpyes, count);
	}

	@Override
	public List<Types> findList(String typeValue) {
		return typeOperatorDao.findList(typeValue);
	}

	@Override
	public Types find(String typeValue) {
		return typeOperatorDao.find(typeValue);
	}

	@Override
	public List<Types> findListFather() {
		return typeOperatorDao.findListFather();
	}


	@Override
	public void saveNewWorkType(String typeName, String typeValue, String fatherType, int typeOrder) {
		typeOperatorDao.doSaveNewWorkType(typeName, typeValue, fatherType, typeOrder);
	}
	
	@Override
	public void doDeleteTypeByTypeId(String typeId) {
		if(!isFather(typeId)){
			typeOperatorDao.doDeleteByTypeId(typeId);
		}else{
			typeOperatorDao.doDeleteFatherAndSon(typeId);
		}
		
	}
	
	//判断是否是父类
	public boolean isFather(String typeId){
		boolean isSon = false;
		List<Types> types = new ArrayList<Types>();
		types = typeOperatorDao.findFather(typeId);
		if(types.size() > 0){
			isSon = true;
		}
		return isSon;
	}

	@Override
	public void updateTypeInfosByTypeId(String typeId, String typeName, String typeValue, int typeOrder, String fatherType) {
		typeOperatorDao.updateTypeInfosByTypeId(typeId, typeName, typeValue, typeOrder, fatherType);
	}
	
	@Override
	public Types queryTypeInfoByTypeId(String typeId) {
		return typeOperatorDao.queryTypeInfoByTypeId(typeId);
	}
	
	public TypeOperatorDao getTypeOperatorDao() {
		return typeOperatorDao;
	}
	
	public void setTypeOperatorDao(TypeOperatorDao typeOperatorDao) {
		this.typeOperatorDao = typeOperatorDao;
	}

}
