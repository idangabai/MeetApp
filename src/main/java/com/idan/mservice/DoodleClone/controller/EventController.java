package com.idan.mservice.DoodleClone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idan.mservice.DoodleClone.entity.Event;
import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;
import com.idan.mservice.DoodleClone.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/show")
	public String showEvents(Model theModel) {
		
		List<Event> theEvents = eventService.findAllEvents();
		
		
		theModel.addAttribute("events", theEvents);
		
		return "event-list";
	}
	
	
	
	@GetMapping("/show/{eventId}")
	public String showEventById(Model theModel, @PathVariable int eventId) {
		System.out.println("hi the id is: " + eventId);
		Event event  =  eventService.findEventById(eventId);
		theModel.addAttribute("event", event);
		
		List<EventOption> options =  event.getOptions();
		theModel.addAttribute("options", options);
		
		List<EventMember> members = event.getMembers();
		theModel.addAttribute("members", members);
		
		//set the checked table
		for(EventOption option : options) {
			System.out.println(option);
		}
		
		
		return "show-event";
	}
}
