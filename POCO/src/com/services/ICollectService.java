package com.services;

import java.util.List;

import com.entities.Collects;

public interface ICollectService {

	void doAddCollect(String userId, String productId);

	void doDelelteCollect(String productId);

	List<Collects> doQueryCollectByUserId(String userId);

}
