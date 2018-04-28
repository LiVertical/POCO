package com.services;

import java.util.List;

import com.entities.Notification;

public interface ISysNotificationService {

	/**
	 * 根据用户ID获取用户通知
	 *
	 * @param userId
	 * @param recordSize 
	 * @param currentPage 
	 * @return
	 */
	List<Notification> notificationListByUserId(String userId, int currentPage, int recordSize);

	/**
	 * 根据ID查询系统通知
	 * @param notifiactionId
	 * @return
	 */
	Notification showNotificationById(String notifiactionId);

	/**
	 * 后台分页查询发布的通知
	 * @param currentPage
	 * @param recordSize
	 * @return
	 */
	List<Notification> queryNotifiactions(String userId, int role, int currentPage, int recordSize);

	int countReceiver();

	int querynotificationCountByUserId(String userId);

	void deleteNotificationByNotificationId(String notifiactionId);

	int adds(String notificationTitle, String notificationInfo, String userId, String userName);

	int queryTotalNotification(String userId, int role);

}
