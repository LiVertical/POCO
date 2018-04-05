package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.constants.CommentConstants;
import com.entities.Comments;
import com.util.UUIDUtil;

public class CommentsDao extends BaseDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void addComments(String commentUser, String productId, String commentDesc) {
		try {
			Comments comment = new Comments();
			comment.setCommentId(UUIDUtil.generateUUID());
			comment.setCommentUser(commentUser);
			comment.setProductId(productId);
			comment.setCommentDesc(commentDesc);
			comment.setCreateTime(new Date());
			comment.setCommentStatus(CommentConstants.COMMENT_STATUS_NORMAL);
			this.getSession().save(comment);
		} catch (Exception e) {
			logger.error("添加评论失败", e);
		}
	}

	public List<Comments> queryAllCommentInfos(String productId, int currentPage, int recordSize) {
		String hql = "FROM Comments c WHERE c.productId = "+productId;
		List<Comments> commentsInfos = new ArrayList<Comments>();
		try {
			commentsInfos = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (Exception e) {
			logger.error("查询评论异常", e);
		}
		return commentsInfos;
	}

	public List<Comments> queryAllCommentInfosOfUser(String commentUser, int currentPage, int recordSize) {
		String hql = "FROM Comments c WHERE c.commentUser = " + commentUser;
		List<Comments> commentsInfosOfUser = new ArrayList<Comments>();
		try {
			commentsInfosOfUser = getSession().createQuery(hql).setFirstResult((currentPage-1)*recordSize).setMaxResults(recordSize).list();
		} catch (Exception e) {
			logger.error("查询用户评论异常", e);
		}
		return commentsInfosOfUser;
	}

	public void deleteCommentByCommentId(String commentId) {
		try {
			String hql = "DELETE FROM Comments c WHERE c.commentId='"+commentId+"'";
			getSession().createQuery(hql).executeUpdate();
		} catch (Exception e) {
			logger.error("删除用户评论失败", e);
		}
	}

	public int queryAllCommentsByUserCount(String commentUser) {
		int commentsCount = 0;
		String hql = "FROM Comments c WHERE c.commentUser = " + commentUser;
		commentsCount = getSession().createQuery(hql).list().size();
		return commentsCount;
	}

	public int queryAllCommentsCount(String productId) {
		int commentsTotals = 0;
		try {
			String hql = "FROM Comments c WHERE c.productId = "+productId;
			commentsTotals = getSession().createQuery(hql).list().size();
		} catch (Exception e) {
			logger.error("查询用户评论数目异常", e);
		}
		return commentsTotals;
	}

}
