package com.user.entity;

public class ContactEntity {
private Long contactId;
private String contactName;
private String emialid;
private Long userId;

public Long getContactId() {
	return contactId;
}
public void setContactId(Long contactId) {
	this.contactId = contactId;
}
public String getContactName() {
	return contactName;
}
public void setContactName(String contactName) {
	this.contactName = contactName;
}
public String getEmialid() {
	return emialid;
}
public void setEmialid(String emialid) {
	this.emialid = emialid;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public ContactEntity(Long contactId, String contactName, String emialid, Long userId) {
	super();
	this.contactId = contactId;
	this.contactName = contactName;
	this.emialid = emialid;
	this.userId = userId;
}
public ContactEntity() {
	super();
}


}
