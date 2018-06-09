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
	
	/**
	 * 分页查询列表
	 * @return
	 */
	public String page() {
		logger.info("WorkTypeAction.page start ······");
		result = new JSONObject();
		try {
			Page<WorkType> workTypes = workTypeService.findPage(currentPage,recordSize);
			result.put("", workTypes.getData());
			result.put("", workTypes.getCount());
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据父类型查询集合
	 * @return
	 */
	public String list(){
		logger.info("WorkTypeAction.list start·····");
		result = new JSONObject();
		try {
			List<WorkType> wts = workTypeService.findList(typeValue);
			result.put("", wts);
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询失败");
			return ERROR;
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
			WorkType wt = workTypeService.find(typeValue);
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
			List<WorkType> wts = workTypeService.findListFather();
			result.put("", wts);
		} catch (Exception e) {
			result.put("returnCode", "-1");
			result.put("returnMsg", "查询失败");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
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
			//类型值不能重复，如果重复，则不保存
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
	
	/**
	 * 修改
	 * @return
	 */
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
			//类型值不能重复，如果重复，则不修改
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
	
	/**
	 * 删除
	 * @return
	 */
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
