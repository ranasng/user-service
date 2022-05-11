package com.user.entity;

import java.util.ArrayList;
import java.util.List;

public class UserDataEntity {
	private Long userId;
	private String name;
	private String address;
	private List<ContactEntity> contact=new ArrayList<ContactEntity>();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<ContactEntity> getContact() {
		return contact;
	}
	public void setContact(List<ContactEntity> contact) {
		this.contact = contact;
	}
	public UserDataEntity(Long userId, String name, String address, List<ContactEntity> contact) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.contact = contact;
	}
	public UserDataEntity(Long userId, String name, String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
	}
	public UserDataEntity() {
		super();
	}
	
	
	

}
