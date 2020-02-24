package ru.stqa.at.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
	@XStreamOmitField
	@Id
	@Column(name="Id")
	private int id = Integer.MAX_VALUE;

	@Expose
	@Column(name="firstname")
	private String contactName;

	@Expose
	@Column(name="lastname")
	private String contactLastName;

	@Expose
	@Column(name="address")
	@Type(type = "text")
	private String contactAddress;

	@Expose
	@Column(name="home")
	@Type(type = "text")
	private String home;

	@Expose
	@Column(name="mobile")
	@Type(type = "text")
	private String mobile;

	@Expose
	@Column(name="work")
	@Type(type = "text")
	private String work;

	@Expose
	@Transient
	private String allPhones;

	@Expose
	@Column(name="email")
	@Type(type = "text")
	private String email;

	@Expose
	@Column(name="email2")
	@Type(type = "text")
	private String email2;

	@Expose
	@Column(name="email3")
	@Type(type = "text")
	private String email3;

	@Expose
	@Transient
	private String allEmails;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="address_in_groups",
					joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
	private Set<GroupData> groups = new HashSet<GroupData>();

	@Expose
	@Column(name="photo")
	@Type(type = "text")
	private String photo;


	public ContactData()
	{
		this.groups = new HashSet<GroupData>();
	}

	public ContactData withPhoto(String photo) {
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

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withGroups(Groups groups) {
		this.groups = groups;
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

	public String getAllPhones() {
		return allPhones;
	}

	public String getAllEmails() {
		return allEmails;
	}

	public String getPhoto() {
		return photo;
	}

	public Groups getGroups() {
		return new Groups(groups);
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

	public ContactData inGroup(GroupData group) {
		groups.add(group);
		return this;
	}

	private Object readResolve() {
		groups = new HashSet<GroupData>();
		return this;
	}

	public boolean hasGroup(GroupData group) {
		for(ContactData contact : group.getContacts()){
			if(contact.getId() == this.getId()){
				return true;
			}
		}
		return false;
	}
}