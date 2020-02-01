package ru.stqa.at.addressbook.model;

import java.util.Objects;

public class ContactData {
	private int id;
	private final String contactName;
	private final String contactLastName;
	private final String contactAddress;
	private final String contactPhone;
	private final String contactEmail;
	private String group;

	public ContactData(int id, String contactName, String contactLastName, String contactAddress, String contactPhone, String contactEmail, String group) {
		this.id = Integer.MAX_VALUE;
		this.contactName = contactName;
		this.contactLastName = contactLastName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.group = group;
	}

	public ContactData(String contactName, String contactLastName, String contactAddress, String contactPhone, String contactEmail, String group) {
		this.id = Integer.MAX_VALUE;
		this.contactName = contactName;
		this.contactLastName = contactLastName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.group = group;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"id=" + id +
						", contactName='" + contactName + '\'' +
						", contactLastName='" + contactLastName + '\'' +
						", contactAddress='" + contactAddress + '\'' +
						", contactEmail='" + contactEmail + '\'' +
						", contactPhone='" + contactPhone + '\'' +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return id == that.id &&
						Objects.equals(contactName, that.contactName) &&
						Objects.equals(contactLastName, that.contactLastName) &&
						Objects.equals(contactAddress, that.contactAddress) &&
						Objects.equals(contactPhone, that.contactPhone) &&
						Objects.equals(contactEmail, that.contactEmail) &&
						Objects.equals(group, that.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, contactName, contactLastName, contactAddress, contactPhone, contactEmail, group);
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
