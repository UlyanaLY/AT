package ru.stqa.at.addressbook.model;

public class ContactData {
	private final String contactName;
	private final String contactLastName;
	private final String contactAddress;
	private final String contactPhone;
	private final String contactEmail;
	private String group;

	public ContactData(String contactName, String contactLastName, String contactAddress, String contactPhone, String contactEmail, String group) {
		this.contactName = contactName;
		this.contactLastName = contactLastName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.group = group;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getGroup() {
		return group;
	}
}
