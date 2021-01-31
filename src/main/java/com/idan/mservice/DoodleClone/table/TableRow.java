package com.idan.mservice.DoodleClone.table;

import java.util.List;

import com.idan.mservice.DoodleClone.entity.OptionCheck;

public class TableRow {

	private String name;
	
	List<OptionCheck> checkedOptions;

	public TableRow(String name, List<OptionCheck> checkedOptions) {
		this.name = name;
		this.checkedOptions = checkedOptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OptionCheck> getCheckedOptions() {
		return checkedOptions;
	}

	public void setCheckedOptions(List<OptionCheck> checkedOptions) {
		this.checkedOptions = checkedOptions;
	}
	
	
	
	
}
