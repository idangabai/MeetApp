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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/{eventId}/showFormForAddMember")
	public String showFormForAddMember(Model theModel, @PathVariable int eventId) {
		Event event = eventService.findEventById(eventId);
		
		EventMember member = new EventMember();
		member.setEvent(event);
		theModel.addAttribute("member", member);
		theModel.addAttribute("eventId", eventId);
		
		
		return "member-form";
	}
	
	
	@GetMapping("/{eventId}/showFormForAddOption")
	public String showFormForAddOption(Model theModel, @PathVariable int eventId) {
		Event event = eventService.findEventById(eventId);
		
		EventOption option = new EventOption();
		
		theModel.addAttribute("option", option);
		theModel.addAttribute("eventId", eventId);
		
		
		return "option-form";
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
		eventService.updateEventMemberOtions(eventId, memberOptions);
		
		return "redirect:/events/show/" +  eventId;
	}
	
	//show event form
	@GetMapping("/showFormForAddEvent")
	public String showEventForm(Model theModel) {
		
		theModel.addAttribute("event", new Event());
		return "event-form";
	}
	
	//create new event
	@PostMapping("/save")
	public String saveNewEvent(@ModelAttribute("event") Event theEvent) {
		theEvent.setId(0);
		eventService.saveEvent(theEvent);
		return "redirect:/events/show";
	}
	
	
	@PostMapping("/{eventId}/members/save")
	public String saveNewEventMember(@ModelAttribute("member") EventMember theMember, @PathVariable int eventId) {
		theMember.setId(0);
		theMember.initChosenOptions();;
		Event event = eventService.findEventById(eventId);
		theMember.setEvent(event);
		event.addMember(theMember);
		eventService.saveMember(theMember);
		eventService.saveEvent(event);
		return "redirect:/events/show/" + eventId;
	}
	
	
	@PostMapping("/{eventId}/options/save")
	public String saveNewEventOption(@ModelAttribute("option") EventOption theOption, @PathVariable int eventId) {
		theOption.setId(0);
		
		Event event = eventService.findEventById(eventId);
		theOption.setEvent(event);
		event.addOption(theOption);
		eventService.saveEventOption(theOption);
		eventService.saveEvent(event);
		return "redirect:/events/show/" + eventId;
	}
	
	//delete an event
	@GetMapping("/delete")
	public String deleteEventById(@RequestParam("eventId") int theId) {
		
		eventService.deleteEventById(theId);
		
		return "redirect:/events/show";	
	}
}
