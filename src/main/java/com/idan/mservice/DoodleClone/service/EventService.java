package com.idan.mservice.DoodleClone.service;

import java.util.List;

import com.idan.mservice.DoodleClone.entity.Event;
import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;

public interface EventService {
	
	//create  - update
	public void saveEvent(Event theEvent);
	
	public void saveMember(EventMember theEventMember);
	
	public void saveEventOption(EventOption theEventOption);
	
	public void updateEventMemberOtions(int eventId,  List<String> memberOptions);
	
	//read
	public Event findEventById(int theId);
	
	public EventOption findOptionById(int theId);
	
	public EventMember findEventMember(int theId);
	
	
	//show all events
	public List<Event> findAllEvents();

	//delete
	public void deleteEventById(int theId);
}
