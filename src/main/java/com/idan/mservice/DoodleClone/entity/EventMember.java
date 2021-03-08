package com.idan.mservice.DoodleClone.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emember")
public class EventMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "member_name")
	private	String name;
	
	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;
	
	
	
	
	public EventMember(int id, String name, Event event, Set<EventOption> chosenOptions) {
		this.id = id;
		this.name = name;
		this.event = event;
		this.chosenOptions = chosenOptions;
	}


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_options",
	joinColumns = { @JoinColumn(name="member_id")},
	inverseJoinColumns = {@JoinColumn(name="option_id")})
	Set<EventOption> chosenOptions;
	
	public void addEventOption(EventOption theEventOption) {
		if(theEventOption == null) {
			chosenOptions  = new HashSet<>();
		}
		chosenOptions.add(theEventOption);
	}
	
	
	public EventMember() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EventOption> getChosenOptions() {
		return chosenOptions;
	}
	
	public void initChosenOptions() {
		this.chosenOptions = new HashSet<>();
	}

	public void setChosenOptions(Set<EventOption> chosenOptions) {
		this.chosenOptions = chosenOptions;
	}
	
	public List<OptionCheck> getCheckedOptions(List<EventOption> allOptions){
		
		List<OptionCheck> checkedList = new ArrayList<>();
		
		for(EventOption currentOption : allOptions) {
			if(chosenOptions.contains(currentOption)) {
				checkedList.add(new OptionCheck(true, currentOption.getId(), this.id) );
			}else {
				checkedList.add(new OptionCheck(false, currentOption.getId(), this.id) );
			}
		}
		
		
		return checkedList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventMember other = (EventMember) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EventMember [id=" + id + ", name=" + name + ", chosenOptions=" + chosenOptions + "]";
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void clearChosenOptions() {
		chosenOptions.clear();
	}
	
	public boolean addOption(EventOption theOption) {
		return chosenOptions.add(theOption);
	}
	
}
