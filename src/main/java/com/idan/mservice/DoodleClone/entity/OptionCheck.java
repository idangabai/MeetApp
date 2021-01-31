package com.idan.mservice.DoodleClone.entity;

public class OptionCheck {
	private Boolean check;
	private int optionId;
	private int memberId;
	
	public OptionCheck() {}
	
	
	public OptionCheck(Boolean check, int optionId, int memberId) {
		this.check = check;
		this.optionId = optionId;
		this.memberId = memberId;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	
	public String getListIdValue() {
		return memberId + "-" + optionId;
	}
	
	
}
