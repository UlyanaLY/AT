package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.at.addressbook.model.GroupData;

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

	public void checkCreatedGroup() {
		click(By.name("selected[]"));
	}

	public void deleteGroup() {
		click(By.name("delete"));
	}
}
