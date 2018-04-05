package com.services.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.dao.CommentsDao;
import com.entities.Comments;
import com.services.ICommentsService;

public class CommentsService implements ICommentsService{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private CommentsDao commentsDao;

	@Override
	public void doAddComments(String commentUser, String productId, String commentDesc) {
		commentsDao.addComments(commentUser, productId, commentDesc);
	}
	@Override
	public List<Comments> queryAllCommentInfos(String productId, int currentPage, int recordSize) {
		return commentsDao.queryAllCommentInfos(productId, currentPage, recordSize);
	}
	
	@Override
	public List<Comments> queryAllCommentInfosbyUser(String commentUser, int currentPage, int recordSize) {
		return commentsDao.queryAllCommentInfosOfUser(commentUser,currentPage, recordSize);
	}
	
	@Override
	public void deleteCommentByCommentId(String commentId) {
		commentsDao.deleteCommentByCommentId(commentId);
	}
	
	@Override
	public int queryCommentsCountOfUser(String commentUser) {
		return commentsDao.queryAllCommentsByUserCount(commentUser);
	}
	
	@Override
	public int queryCommentsCounts(String productId) {
		return commentsDao.queryAllCommentsCount(productId);
	}
	
	public CommentsDao getCommentsDao() {
		return commentsDao;
	}

	public void setCommentsDao(CommentsDao commentsDao) {
		this.commentsDao = commentsDao;
	}
}
