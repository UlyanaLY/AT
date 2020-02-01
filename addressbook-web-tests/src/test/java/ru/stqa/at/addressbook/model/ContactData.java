package ru.stqa.at.addressbook.model;

import java.util.Objects;

public class ContactData {
	private final String id;
	private final String contactName;
	private final String contactLastName;
	private final String contactAddress;
	private final String contactPhone;
	private final String contactEmail;
	private String group;

	public ContactData(String contactName, String contactLastName, String contactAddress, String contactPhone, String contactEmail, String group) {
		this.id = null;
		this.contactName = contactName;
		this.contactLastName = contactLastName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public ContactData(String id, String contactName, String contactLastName, String contactAddress, String contactPhone, String contactEmail, String group) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return Objects.equals(contactName, that.contactName) &&
						Objects.equals(contactLastName, that.contactLastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactName, contactLastName);
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"contactName='" + contactName + '\'' +
						", contactLastName='" + contactLastName + '\'' +
						'}';
	}

	public String getGroup() {
		return group;
	}
}
