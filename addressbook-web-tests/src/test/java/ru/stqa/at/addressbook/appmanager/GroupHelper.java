package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupHelper {
	private WebDriver wd;

	public GroupHelper(WebDriver wd) {
		this.wd=wd;
	}

	public void submitGroupForm() {
		wd.findElement(By.name("submit")).click();
	}

	public void fillGroupForm(GroupData groupData) {
		wd.findElement(By.name("group_name")).click();
		wd.findElement(By.name("group_name")).clear();
		wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
		wd.findElement(By.name("group_header")).click();
		wd.findElement(By.name("group_header")).clear();
		wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
		wd.findElement(By.name("group_footer")).click();
		wd.findElement(By.name("group_footer")).clear();
		wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
	}

	public void initGroupCreation() {
		wd.findElement(By.name("new")).click();
	}

	public void checkCreatedGroup() {
		wd.findElement(By.name("selected[]")).click();
	}

	public void deleteGroup() {
		wd.findElement(By.name("delete")).click();
	}
}
