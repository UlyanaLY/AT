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

	public void initGroupModification() {
		click(By.name("edit"));
	}

	public void checkCreatedGroup() {
		click(By.name("selected[]"));
	}

	public void deleteGroup() {
		click(By.name("delete"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}

	public void createAGroup(GroupData group) {
		initGroupCreation();
		fillGroupForm(group);
		submitGroupForm();
		goToGroupPage();
		checkCreatedGroup();
	}

	public boolean isThereAGroup() {
		return isElementPresent(By.name("selected[]"));
	}

	public void goToGroupPage() {
		if (isElementPresent(By.tagName("h1"))
						&& wd.findElement(By.tagName("h1")).getText().equals("Groups")
						&& isElementPresent(By.name("new"))) {
			return;
		} else {
			click(By.linkText("groups"));
		}
	}
}
