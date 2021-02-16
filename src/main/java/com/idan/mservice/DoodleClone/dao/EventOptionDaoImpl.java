package com.idan.mservice.DoodleClone.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idan.mservice.DoodleClone.entity.EventOption;

@Repository
public class EventOptionDaoImpl implements EventOptionDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public EventOption findEventOptionById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		EventOption theOption = currentSession.get(EventOption.class, theId);
		return theOption;
	}

	@Override
	public void save(EventOption theOption) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theOption);
		
	}

}
