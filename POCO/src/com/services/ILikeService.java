package com.services;

import java.util.List;

import com.entities.Likes;
import com.entities.ProductInfo;


public interface ILikeService {

	void doCancleLike(String id);

	int doGetLikesNum(String productId);

	List<Likes> setTags(String userId, String productId, String productName);

	List<Likes> queryProductByUserId(String userId);

}
