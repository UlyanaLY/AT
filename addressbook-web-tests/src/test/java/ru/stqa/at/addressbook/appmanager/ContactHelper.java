package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactHelper {
	private WebDriver wd;

	public ContactHelper(WebDriver wd) {
		this.wd=wd;
	}

	public void checkCreatedContact() {
		wd.findElement(By.id("3")).click();
	}

	public void submitContactForm() {
		wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	}

	public void fillContactForm(ContactData contactdata) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(contactdata.getContactName());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(contactdata.getContcatLastName());
		wd.findElement(By.name("address")).click();
		wd.findElement(By.name("address")).clear();
		wd.findElement(By.name("address")).sendKeys(contactdata.getContactAddress());
		wd.findElement(By.name("mobile")).click();
		wd.findElement(By.name("mobile")).clear();
		wd.findElement(By.name("mobile")).sendKeys(contactdata.getContactPhone());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(contactdata.getContactEmail());
	}
}
