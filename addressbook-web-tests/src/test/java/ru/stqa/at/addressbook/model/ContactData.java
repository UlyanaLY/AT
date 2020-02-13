package ru.stqa.at.addressbook.model;

import java.io.File;
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
	private String email2;
	private String email3;
	private String allEmails;
	private String group;
	private File photo;

	public ContactData withPhoto(File photo) {
		this.photo = photo;
		return this;
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
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

	public ContactData withEmail2(String contactEmail2) {
		this.email2 = contactEmail2;
		return this;
	}

	public ContactData withEmail3(String contactEmail3) {
		this.email3 = contactEmail3;
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

	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}

	public String getGroup() {
		return group;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getAllEmails() {
		return allEmails;
	}

	public File getPhoto() {
		return photo;
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