package com.idan.mservice.DoodleClone.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;

@Repository
public class EventMemberDaoImpl implements EventMemberDao {


	@Autowired
	private EntityManager entityManager;
	
	@Override
	public EventMember findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		EventMember theEvent =  currentSession.get(EventMember.class, theId);
		
		return theEvent;
	}

	@Override
	public void save(EventMember theEventMember) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEventMember);

	}

	@Override
	public void updateMemberOptions(EventMember theEventMember, List<EventOption> updatedOptions) {
		Session currentSession = entityManager.unwrap(Session.class);
		EventMember theMember =  currentSession.get(EventMember.class, theEventMember.getId());
		theMember.setChosenOptions(updatedOptions);
		currentSession.saveOrUpdate(theMember);
		
		
	}

}
