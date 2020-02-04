package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void contactPage() {
		if (isElementPresent(By.tagName("h1"))
						&& wd.findElement(By.tagName("h1")).getText().equals("isElementPresent(By.tagName(\"h1\"))")) {
			return;
		} else {
			click(By.linkText("home"));
		}
	}

	public void groupPage() {
		if (isElementPresent(By.tagName("h1"))
						&& wd.findElement(By.tagName("h1")).getText().equals("Groups")
						&& isElementPresent(By.name("new"))) {
			return;
		} else {
			click(By.linkText("groups"));
		}
	}

}
