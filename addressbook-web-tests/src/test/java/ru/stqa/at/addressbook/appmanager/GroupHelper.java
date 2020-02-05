package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase{

	public GroupHelper(WebDriver wd) {
		super(wd);
	}

	public void submitGroupForm() {
		click(By.name("submit"));
	}

	public void fillGroupForm(GroupData groupData) {
		type( By.name("group_name"), groupData.getGroupName());
		type( By.name("group_header"), groupData.getHeader());
		type( By.name("group_footer"), groupData.getFooter());
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void initGroupModification() {
		click(By.name("edit"));
	}

	//public void select(int index) {
	//	wd.findElements(By.name("selected[]")).get(index).click();
	//}

	public void selectGroupById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
	}

	public void deleteGroup() {
		click(By.name("delete"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}

	public void create(GroupData group) {
		initGroupCreation();
		fillGroupForm(group);
		submitGroupForm();
	}

	public void modify(GroupData group) {
		selectGroupById(group.getId());
		initGroupModification();
		fillGroupForm(group);
		submitGroupModification();
	}
//
//	public void delete(int index) {
//		select(index);
//    deleteGroup();
//	}

	public void delete(GroupData group) {
		selectGroupById(group.getId());
		deleteGroup();
	}

	public List<GroupData> list() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element: elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			groups.add(new GroupData().withId(id).withName(name));
		}

		return groups;
	}

	public Groups all() {
		Groups groups = new Groups();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element: elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			groups.add(new GroupData().withId(id).withName(name));
		}

		return groups;
	}
}
