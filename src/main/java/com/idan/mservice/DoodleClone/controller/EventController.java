package com.idan.mservice.DoodleClone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idan.mservice.DoodleClone.entity.Event;
import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;
import com.idan.mservice.DoodleClone.service.EventService;
import com.idan.mservice.DoodleClone.table.TablePage;

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
		
		Event event  =  eventService.findEventById(eventId);
		theModel.addAttribute("event", event);
		
		List<EventOption> options =  event.getOptions();
		theModel.addAttribute("options", options);
		
		List<EventMember> members = event.getMembers();
		theModel.addAttribute("members", members);
		
		//set the checked table
		TablePage tablePage = new TablePage(event, options, members);
		theModel.addAttribute("tablePage", tablePage);
		
		return "show-event";
	}
	
	
	@PostMapping("/updateTable")
	public String updateTable(@ModelAttribute TablePage tablePage, Model theModel) {
		
		System.out.println(tablePage);
		int eventId  =  tablePage.getEventId();
		List<String> memberOptions =  tablePage.getCheckItems();
		System.out.println("===>>>> update table!!!");
		eventService.updateEventMemberOtions(eventId, memberOptions);
		
		return "redirect:/events/show/" +  eventId;
	}
}
