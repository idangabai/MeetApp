package com.idan.mservice.DoodleClone.dao;

import java.util.List;

import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;

public interface EventMemberDao {

	public EventMember findById(int theId);
	
	public void save(EventMember theEventMember);
	
	public void updateMemberOptions(EventMember theEventMember, List<EventOption> updatedOptions);
	
}
