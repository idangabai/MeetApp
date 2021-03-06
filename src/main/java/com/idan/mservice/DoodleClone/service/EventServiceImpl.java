package com.idan.mservice.DoodleClone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
		eventOptionDao.save(theEventOption);
		
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

	@Override
	@Transactional
	public void updateEventMemberOtions(int eventId, List<String> memberOptions) {
		Event event =  eventDao.findEventsById(eventId);
		if(event == null) {
			//maybe throw exception??
			return;
		}
		List<EventOption> options  =  event.getOptions();
		List<EventMember> members = event.getMembers();
		Map<EventMember, List<EventOption>> seenMembers = new HashMap<>();
		
//		Map<EventOption, Set<EventMember>> updatedEventOptionsMembers =  new HashMap<>();
 		
		//1
//		System.out.println(memberOptions);
		
		for(String memberOption : memberOptions) {
			String[] ids =  memberOption.split("-");
			
			try {
				
				int memberId = Integer.valueOf( ids[0]);
				int optionId = Integer.valueOf( ids[1]);
				
				EventOption theOption = findOptionFromOptions(optionId, options); 
				
				EventMember theMember = findMemberFromMembers(memberId, members);
				
				if(! seenMembers.containsKey(theMember)) {
					seenMembers.put(theMember,new ArrayList<>());
				}
				
				List<EventOption> memberOptionsList = seenMembers.get(theMember);
				memberOptionsList.add(theOption);
				seenMembers.put(theMember, memberOptionsList);
				

				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		for(EventMember member : members) {
			if(! seenMembers.containsKey(member)) {
				member.setChosenOptions(new HashSet<>());
				
			}else {
				Set<EventOption> chosenOption =seenMembers.get(member).stream().collect(Collectors.toSet()); 
				member.setChosenOptions(chosenOption);
			}
			eventMemberDao.save(member);
			
			
		}
		

		
	}

	private EventMember findMemberFromMembers(int memberId, List<EventMember> members) {
		for(EventMember member : members) {
			if(member.getId() == memberId) {
				return member;
			}
		}
		throw new RuntimeException("Member id not exist");
	}

	private EventOption findOptionFromOptions(int optionId, List<EventOption> options) {
		for(EventOption option : options) {
			if(option.getId() == optionId) {
				return option;
			}
		}
		throw new RuntimeException("Option id not exist");
//		return null;
	}

	@Override
	@Transactional
	public void deleteEventById(int theId) {
		Event event = eventDao.findEventsById(theId);
		if(event ==null) {
			return; //maybe throw an error
		}
		eventDao.deleteById(theId);
		
	}


}
