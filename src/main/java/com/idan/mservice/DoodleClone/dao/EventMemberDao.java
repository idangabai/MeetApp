package com.idan.mservice.DoodleClone.dao;

import com.idan.mservice.DoodleClone.entity.EventMember;

public interface EventMemberDao {

	public EventMember findById(int theId);
	
	public void save(EventMember theEventMember);
	
}
