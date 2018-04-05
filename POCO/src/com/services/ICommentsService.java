package com.services;

import java.util.List;

import com.entities.Comments;

public interface ICommentsService {

	void doAddComments(String commentUser, String productId, String commentDesc);

	List<Comments> queryAllCommentInfos(String productId, int currentPage, int recordSize);

	void deleteCommentByCommentId(String commentId);

	int queryCommentsCountOfUser(String commentUser);

	int queryCommentsCounts(String productId);

	List<Comments> queryAllCommentInfosbyUser(String commentUser, int currentPage, int recordSize);


}
