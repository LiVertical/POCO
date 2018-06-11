package com.services;

import java.util.List;

import com.entities.Types;
import com.util.Page;

public interface ITypeOperatorService {

	Page<Types> findPage(int currentPage, int recordSize);

	List<Types> findList(String typeValue);

	Types find(String typeValue);

	List<Types> findListFather();

	void saveNewWorkType(String typeName, String typeValue, String fatherType, int typeOrder);

	void doDeleteTypeByTypeId(String typeId);

	void updateTypeInfosByTypeId(String typeId, String typeName, String typeValue, int typeOrder, String fatherType);

	Types queryTypeInfoByTypeId(String typeId);

}
