package ru.stqa.at.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table( name = "group_list" )
public class GroupData {
	@XStreamOmitField
	@Id
	@Column(name = "group_id")
	private int id = Integer.MAX_VALUE;

	@Expose
	@Column(name = "group_name")
	private String groupName;

	@Expose
	@Column(name = "group_header")
	@Type(type = "text")
	private String header;

	@Expose
	@Column(name = "group_footer")
	@Type(type = "text")
	private String footer;

	@ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
	private Set<ContactData> contacts = new HashSet<ContactData>();

	public Set<ContactData> getContacts() {
		return new Contacts(contacts);
	}

	public GroupData withId(int id) {
		this.id = id;
		return this;
	}

	public GroupData withName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public GroupData withHeader(String header) {
		this.header = header;
		return this;
	}

	public GroupData withFooter(String footer) {
		this.footer = footer;
		return this;
	}

	public int getId() {
		return id;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GroupData groupData = (GroupData) o;
		return id == groupData.id &&
						Objects.equals(groupName, groupData.groupName) &&
						Objects.equals(header, groupData.header) &&
						Objects.equals(footer, groupData.footer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, groupName, header, footer);
	}

	@Override
	public String toString() {
		return "GroupData{" +
						"id='" + id + '\'' +
						", groupName='" + groupName + '\'' +
						'}';
	}

	public boolean hasContact(ContactData contact) {
		for(GroupData group : contact.getGroups()){
			if(group.getId() == this.getId()){
				return true;
			}
		}
		return false;
	}
}