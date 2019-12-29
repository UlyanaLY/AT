package ru.stqa.at.addressbook;

public class Contactdata {
	private final String contactName;
	private final String contcatLastName;
	private final String contactAddress;
	private final String contactPhone;
	private final String contactEmail;

	public Contactdata(String contactName, String contcatLastName, String contactAddress, String contactPhone, String contactEmail) {
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
