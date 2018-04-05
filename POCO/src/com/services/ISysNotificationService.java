package com.services;

import java.util.List;

import com.entities.Notifiaction;

public interface ISysNotificationService {

	/**
	 * 根据用户ID获取用户通知
	 *
	 * @param userId
	 * @return
	 */
	List<Notifiaction> notificationListByUserId(int userId);

	/**
	 * 根据ID查询系统通知
	 * @param notifiactionId
	 * @return
	 */
	Notifiaction showNotificationById(String notifiactionId);

	/**
	 * 创建系统通知
	 * @param notifiaction
	 */
	void add(Notifiaction notifiaction);


}
