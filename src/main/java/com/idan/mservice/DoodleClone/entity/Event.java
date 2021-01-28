package com.idan.mservice.DoodleClone.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	public Event() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Event [Id=" + id + ", title=" + title + "]";
	}
	
	@OneToMany(mappedBy ="event" , cascade = CascadeType.ALL)
	private List<EventOption> options;

	
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<EventMember> members;
	
	
	
	public Event(int id, String title, List<EventOption> options, List<EventMember> members) {
		this.id = id;
		this.title = title;
		this.options = options;
		this.members = members;
	}

	public void addOption(EventOption theEventOption) {
		if(options == null) {
			options = new ArrayList<>();
		}
		options.add(theEventOption);
	}
	
	public void addMember(EventMember theEventMember) {
		if(members == null) {
			members = new ArrayList<>();
		}
		members.add(theEventMember);
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
	
	
}
