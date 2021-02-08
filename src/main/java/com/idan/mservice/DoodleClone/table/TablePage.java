package com.idan.mservice.DoodleClone.table;

import java.util.ArrayList;
import java.util.List;

import com.idan.mservice.DoodleClone.entity.Event;
import com.idan.mservice.DoodleClone.entity.EventMember;
import com.idan.mservice.DoodleClone.entity.EventOption;

public class TablePage {

	private Event event;
	
	private int eventId;
	
	private List<EventOption> options;
	
	private List<EventMember> members;
	
	
	private List<String> checkItems;
	
	private List<TableRow> rows;
	
	public TablePage() {
		checkItems = new ArrayList<>();
	}

	
	public TablePage(Event event, List<EventOption> options, List<EventMember> members) {
		this.event = event;
		eventId = event.getId();
		this.options = options;
		this.members = members;
		checkItems = new ArrayList<>();
		rows = new ArrayList<>();
		for(EventMember member : members) {
			TableRow row = new TableRow(member.getName(), member.getCheckedOptions(options));
			
			rows.add(row);
		}
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public List<EventOption> getOptions() {
		return options;
	}


	public void setOptions(List<EventOption> options) {
		this.options = options;
	}


	public List<EventMember> getMembers() {
		return members;
	}


	public void setMembers(List<EventMember> members) {
		this.members = members;
	}


	public List<String> getCheckItems() {
		return checkItems;
	}


	public void setCheckItems(List<String> checkItems) {
		this.checkItems = checkItems;
	}


	public List<TableRow> getRows() {
		return rows;
	}


	public void setRows(List<TableRow> rows) {
		this.rows = rows;
	}


	public int getEventId() {
		return eventId;
	}


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	
	
	
}
