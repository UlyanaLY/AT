package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void goToHomePage() {
		if (isElementPresent(By.id("maintainable"))) {
			return;
		}
		click(By.linkText("home"));
	}
	
	public void goToCreateContactPage() {
		if (isElementPresent(By.tagName("h1"))
						&& wd.findElement(By.tagName("h1")).getText().equals("isElementPresent(By.tagName(\"h1\"))")) {
			return;
		} else {
			click(By.linkText("add new"));
		}
	}
}
