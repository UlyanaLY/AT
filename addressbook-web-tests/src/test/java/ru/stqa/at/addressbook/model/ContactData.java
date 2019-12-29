package ru.stqa.at.addressbook.model;

public class ContactData {
	private final String contactName;
	private final String contcatLastName;
	private final String contactAddress;
	private final String contactPhone;
	private final String contactEmail;

	public ContactData(String contactName, String contcatLastName, String contactAddress, String contactPhone, String contactEmail) {
		this.contactName = contactName;
		this.contcatLastName = contcatLastName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContcatLastName() {
		return contcatLastName;
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
}
