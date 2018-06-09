package com.actions;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.entities.WorkType;
import com.opensymphony.xwork2.ActionSupport;
import com.services.IWorkTypeService;
import com.util.LoginUserUtil;
import com.util.Page;

public class WorkTypeAction extends ActionSupport{

	Logger logger = Logger.getLogger(WorkTypeAction.class);
	private JSONObject result;
	private WorkType workType;
	private IWorkTypeService workTypeService;
	private int currentPage;
	private int recordSize;
	private String typeValue;
	
	public String page() {
		logger.info("WorkAction.queryAllWorks start ······");
		result = new JSONObject();
		
		Page<WorkType> workTypes = workTypeService.findPage(currentPage,recordSize);
		
		result.put("", workTypes.getData());
		result.put("", workTypes.getCount());
		return SUCCESS;
	}
	
	public String list(){
		List<WorkType> workTypesEs = workTypeService.findList(typeValue);
		return SUCCESS;
	}
	
	public String find(){
		WorkType workType = workTypeService.find(typeValue);
		return SUCCESS;
	}
	
	public String findListFather(){
		List<WorkType> workTypes = workTypeService.findListFather();
		return SUCCESS;
	}
	public String add(){
		logger.info("WorkTypeAction.add start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return ERROR;
		}
		try {
			WorkType wt = workTypeService.find(workType.getTypeValue());
			if (wt != null) {
				throw new Exception();
			}
			workTypeService.save(workType);
			result.put("returnCode", "00");
			result.put("returnMsg", "保存成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "保存失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String update(){
		logger.info("WorkTypeAction.update start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return ERROR;
		}
		try {
			WorkType wt = workTypeService.find(workType.getTypeValue());
			if (wt != null) {
				throw new Exception();
			}
			workTypeService.update(workType);
			result.put("returnCode", "00");
			result.put("returnMsg", "修改成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "修改失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String delete(){
		logger.info("WorkTypeAction.delete start·····");
		result = new JSONObject();
		String userId = LoginUserUtil.getUserInfo().getUserId();
		if(StringUtils.isBlank(userId)){
			result.put("returnCode", "10");
			result.put("returnMsg", "参数错误");
			return ERROR;
		}
		try {
			workTypeService.delete(workType);
			result.put("returnCode", "00");
			result.put("returnMsg", "删除成功");
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "删除失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
}
