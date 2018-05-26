package com.services;

public interface IVoteService {

	boolean doVote(String userId, String workId);

	int getVoteNum(String workId);

}
