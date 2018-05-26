package com.dao;

import java.util.Date;

import com.entities.Vote;
import com.util.UUIDUtil;

public class VoteDao extends BaseDao {

	public boolean findWorkByUserId(String workId, String userId) {
		boolean isExist = true;
		String sql = "FROM Work Where workId='"+workId+"' AND userId='"+ userId+"'";
		int size = getSession().createQuery(sql).list().size();
		if(size>0){
			isExist = false;
		}
		return isExist;
	}

	public void saveVote(String workId, String userId) {
		Vote vote = new Vote();
		vote.setCreateTime(new Date());
		vote.setUserId(userId);
		vote.setWorkId(workId);
		vote.setVoteId(UUIDUtil.generateUUID());
		this.getSession().save(vote);
	}

	public int getVoteNum(String workId) {
		String sql = "FROM Work WHERE workId='"+workId+"'";
		return getSession().createQuery(sql).list().size();
	}

}
