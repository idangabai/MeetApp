package com.idan.mservice.DoodleClone.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idan.mservice.DoodleClone.entity.EventMember;

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

}
