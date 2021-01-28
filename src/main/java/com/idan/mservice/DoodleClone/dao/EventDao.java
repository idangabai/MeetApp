package com.idan.mservice.DoodleClone.dao;

import java.util.List;

import com.idan.mservice.DoodleClone.entity.Event;

public interface EventDao {
	public List<Event> findEvents();
	public Event findEventsById(int theId);
	
	public void save(Event theEvent);
	
	public void deleteById(int theId);
	
	
}
