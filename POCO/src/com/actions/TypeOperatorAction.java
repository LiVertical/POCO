package com.actions;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.entities.Types;
import com.opensymphony.xwork2.ActionSupport;
import com.services.ITypeOperatorService;
import com.util.LoginUserUtil;

public class TypeOperatorAction extends ActionSupport{

	Logger logger = Logger.getLogger(TypeOperatorAction.class);
	private JSONObject result;
	private Types workType;
	private ITypeOperatorService typeOperatorService;
	private int currentPage;
	private int recordSize;
	private String typeValue;
	private String typeName;
	private int typeOrder;
	private String fatherType;
	private String typeId;
	
	/**
	 * 分页查询列表
	 * @return
	 */
	public String queryAllWorkTypeInfo() {
		logger.info("WorkTypeAction.page start ······");
		result = new JSONObject();
		try {
			result.put("workTypeInfo", typeOperatorService.findPage(currentPage,recordSize).getData());
			result.put("workTypeCount", typeOperatorService.findPage(currentPage,recordSize).getCount());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询作品分类信息成功");
		} catch (Exception e) {
			logger.error("查询作品分类信息失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 根据父类型查询集合
	 * @return
	 */
	public String querySonTypeByFatherType(){
		logger.info("WorkTypeAction.querySonTypeByFatherType start·····");
		result = new JSONObject();
		try {
			if(StringUtils.isBlank(typeValue)){
				result.put("returnCode", "10");
				result.put("returnMsg", "参数错误");
			}
			result.put("workTypeInfo", typeOperatorService.findList(typeValue));
			result.put("retunCode", "00");
			result.put("returnMsg", "查询成功！");
		} catch (Exception e) {
			logger.error("查询子类信息失败", e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 根据类型查询
	 * @return
	 */
	public String find(){
		logger.info("WorkTypeAction.list start·····");
		result = new JSONObject();
		try {
			Types wt = typeOperatorService.find(typeValue);
			result.put("", wt);
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询一级类型集合 （没有父类型的为一级类型）
	 * @return
	 */
	public String findListFather(){
		logger.info("WorkTypeAction.list start·····");
		result = new JSONObject();
		try {
			result.put("firstWts", typeOperatorService.findListFather());
			result.put("returnCode", "00");
			result.put("returnMsg", "查询成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("查询一级类型失败", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String addNewWorkType(){
		logger.info("WorkTypeAction.addNewWorkType start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			//类型值不能重复，如果重复，则不保存
			Types wt = typeOperatorService.find(typeValue);
			if (wt != null) {
				result.put("returnCode", "02");
				result.put("returnMsg", "该类型已经存在");
				return SUCCESS;
			}
			typeOperatorService.saveNewWorkType(typeName, typeValue, fatherType, typeOrder);
			result.put("returnCode", "00");
			result.put("returnMsg", "保存成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "保存失败");
			logger.error("保存新类型异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateTypeInfoByTypeId(){
		logger.info("WorkTypeAction.updateTypeInfoByTypeId start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			//类型值不能重复，如果重复，则不修改
			Types wt = typeOperatorService.find(workType.getTypeValue());
			if (wt != null) {
				result.put("returnCode", "02");
				result.put("returnMsg", "类型值重复");
				return SUCCESS;
			}
			typeOperatorService.updateTypeInfosByTypeId(typeId, typeName, typeValue, typeOrder, fatherType);
			result.put("returnCode", "00");
			result.put("returnMsg", "修改成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
			logger.error("修改类型信息失败", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String deleteWorkTypeByWorkId(){
		logger.info("WorkTypeAction.delete start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			typeOperatorService.doDeleteTypeByTypeId(typeId);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "删除失败");
		}
		return SUCCESS;
	}
	
	//根据typeId查询类型信息
	public String queryTypeInfoByTypeId(){
		logger.info("WorkTypeAction.queryTypeInfoByTypeId start·····");
		result = new JSONObject();
		if(StringUtils.isBlank(typeId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return SUCCESS;
		}
		try {
			result.put("typeInfo", typeOperatorService.queryTypeInfoByTypeId(typeId));
			result.put("returnCode", "00");
			result.put("returnMsg", "查询类型信息成功");
		} catch (Exception e) {
			logger.error("查询类型信息失败" ,e);
			result.put("returnCode", "-1");
			result.put("returnMsg", "内部服务器异常");
		}
		return SUCCESS;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public Types getWorkType() {
		return workType;
	}

	public void setWorkType(Types workType) {
		this.workType = workType;
	}

	public ITypeOperatorService getTypeOperatorService() {
		return typeOperatorService;
	}

	public void setTypeOperatorService(ITypeOperatorService typeOperatorService) {
		this.typeOperatorService = typeOperatorService;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}

	public String getFatherType() {
		return fatherType;
	}

	public void setFatherType(String fatherType) {
		this.fatherType = fatherType;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
