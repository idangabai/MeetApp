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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "member_options",
	joinColumns = @JoinColumn(name="member_id"),
	inverseJoinColumns = @JoinColumn(name="option_id"))
	List<EventOption> chosenOptions;
	
	public void addEventOption(EventOption theEventOption) {
		if(theEventOption == null) {
			chosenOptions  = new ArrayList<>();
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

	public List<EventOption> getChosenOptions() {
		return chosenOptions;
	}

	public void setChosenOptions(List<EventOption> chosenOptions) {
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
	
	
}
