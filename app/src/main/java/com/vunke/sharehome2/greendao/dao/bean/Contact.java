package com.vunke.sharehome2.greendao.dao.bean;

import com.vunke.sharehome2.utils.PinyinUtils;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CONTACT".
 */
public class Contact implements Comparable<Contact> {

	private Long userId;
	private String face;
	private String companyPhone;
	private String userName;
	private String email;
	private String groupId;
	private String firstName;
	private String lastName;
	private Boolean isRcs;
	private String homePhone;
	private String contactName;
	private String Pinyin = "";

	public Contact() {

	}

	public Contact(Long userId) {
		this.userId = userId;
	}

	public Contact(Long userId, String face, String companyPhone,
			String userName, String email, String groupId, String firstName,
			String lastName, Boolean isRcs, String homePhone, String contactName) {
		this.userId = userId;
		this.face = face;
		this.companyPhone = companyPhone;
		this.userName = userName;
		this.email = email;
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isRcs = isRcs;
		this.homePhone = homePhone;
		this.contactName = contactName;
		this.Pinyin = PinyinUtils.getPinyin(contactName);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsRcs() {
		return isRcs;
	}

	public void setIsRcs(Boolean isRcs) {
		this.isRcs = isRcs;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPinyin() {
		return Pinyin;
	}

	public void setPinyin(String pinyin) {
		Pinyin = pinyin;
	}

	@Override
	public int compareTo(Contact another) {
		String pinyin2 = "";
		if (another==null) {
			
		}else {
			 pinyin2 = another.getPinyin();
			if (null==pinyin2) {
				pinyin2 = "";
			}
		}
		
		return this.Pinyin.compareTo(pinyin2);
	}
}