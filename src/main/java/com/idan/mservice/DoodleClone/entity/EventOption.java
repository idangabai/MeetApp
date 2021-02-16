package com.idan.mservice.DoodleClone.entity;

import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eoption")
public class EventOption {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_options",
	joinColumns = {@JoinColumn(name="option_id")} ,
	inverseJoinColumns = {@JoinColumn(name="member_id")})
	Set<EventMember> membersChose;
	
	
	
	public EventOption() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}



	public EventOption(int id, String startTime, String endTime, Event event, Set<EventMember> membersChose) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.event = event;
		this.membersChose = membersChose;
	}

	public String showTime() {
		return startTime + "-" + endTime;
	}

	@Override
	public String toString() {
		return "EventOption [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public Set<EventMember> getMembersChose() {
		return membersChose;
	}

	public void setMembersChose(Set<EventMember> membersChose) {
		this.membersChose = membersChose;
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
		EventOption other = (EventOption) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
