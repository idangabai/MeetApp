package com.idan.mservice.DoodleClone.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idan.mservice.DoodleClone.entity.Event;

@Repository
public class EventDaoImpl implements EventDao {


	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Event> findEvents() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Event> theQuery =  currentSession.createQuery("from Event", Event.class);
			
		List<Event> events = theQuery.getResultList();
		
		return events;
	}

	@Override
	public Event findEventsById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Event theEvent =  currentSession.get(Event.class, theId);
		
		return theEvent;
	}

	@Override
	public void save(Event theEvent) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEvent);
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Event where id=:eventID");
		theQuery.setParameter("eventID", theId);
		theQuery.executeUpdate();
	}

}
