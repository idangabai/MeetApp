package com.idan.mservice.DoodleClone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idan.mservice.DoodleClone.dao.EventDao;
import com.idan.mservice.DoodleClone.dao.EventMemberDao;
import com.idan.mservice.DoodleClone.dao.EventOptionDao;
import com.idan.mservice.DoodleClone.entity.Event;
import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;
	
	
	@Autowired
	private EventOptionDao eventOptionDao;
	
	
	@Autowired
	private EventMemberDao eventMemberDao;
	
	
	@Override
	@Transactional
	public void saveEvent(Event theEvent) {
		eventDao.save(theEvent);

	}

	@Override
	@Transactional
	public void saveEventOption(EventOption theEventOption) {
		return;
		
	}
	
	
	@Override
	@Transactional
	public void saveMember(EventMember theEventMember) {
		eventMemberDao.save(theEventMember);

	}



	@Override
	@Transactional
	public Event findEventById(int theId) {
		Event theEvent = eventDao.findEventsById(theId);
		return theEvent;
	}

	@Override
	@Transactional
	public EventOption findOptionById(int theId) {
		EventOption theEventOption = eventOptionDao.findEventOptionById(theId);
		return theEventOption;
	}

	@Override
	@Transactional
	public EventMember findEventMember(int theId) {
		EventMember theEventMember =  eventMemberDao.findById(theId);
		return theEventMember;
	}

	@Override
	@Transactional
	public List<Event> findAllEvents() {
		List<Event> events = eventDao.findEvents();
		return events;
	}


}
