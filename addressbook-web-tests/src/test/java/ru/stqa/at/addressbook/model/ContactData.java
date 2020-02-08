package ru.stqa.at.addressbook.model;

import java.util.Objects;

public class ContactData {
	private int id = Integer.MAX_VALUE;
	private String contactName;
	private String contactLastName;
	private String contactAddress;
	private String home;
	private String mobile;
	private String work;
	private String allPhones;
	private String email;
	private String group;

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}


	public ContactData withName(String contactName) {
		this.contactName = contactName;

		return this;
	}

	public ContactData withLastName(String contactLastName) {
		this.contactLastName = contactLastName;
		return this;
	}

	public ContactData withAddress(String contactAddress) {
		this.contactAddress = contactAddress;
		return this;
	}

	public ContactData withHomePhone(String home) {
		this.home = home;
		return this;
	}

	public ContactData withMobilePhone(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withWorkPhone(String work) {
		this.work = work;
		return this;
	}

	public ContactData withEmail(String contactEmail) {
		this.email = contactEmail;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public int getId() {
		return id;
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

	public String getHomePhone() {
		return home;
	}

	public String getMobilePhone() {
		return mobile;
	}

	public String getWorkPhone() {
		return work;
	}

	public String getEmail() {
		return email;
	}

	public String getGroup() {
		return group;
	}

	public String getAllPhones() {
		return allPhones;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return id == that.id &&
						Objects.equals(contactName, that.contactName) &&
						Objects.equals(contactLastName, that.contactLastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, contactName, contactLastName);
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"contactName='" + contactName + '\'' +
						", contactLastName='" + contactLastName + '\'' +
						'}';
	}
}
