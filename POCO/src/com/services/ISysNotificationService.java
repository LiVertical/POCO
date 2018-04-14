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
	List<Notifiaction> notificationListByUserId(String userId);

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
	int adds(Notifiaction notifiaction);

	/**
	 * 删除通知
	 * @param notifiaction2
	 */
	void delete(Notifiaction notifiaction2);

	/**
	 * 后台分页查询发布的通知
	 * @param currentPage
	 * @param recordSize
	 * @return
	 */
	List<Notifiaction> queryNotifiactions(int currentPage, int recordSize);

	int queryTotalNotification();

	int countReceiver();


}
