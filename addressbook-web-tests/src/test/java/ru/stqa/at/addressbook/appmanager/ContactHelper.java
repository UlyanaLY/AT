package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void checkCreatedContact() {
		click(By.id("3"));
	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void fillContactForm(ContactData contactdata) {
		type( By.name("firstname"), contactdata.getContactName());
		type( By.name("lastname"), contactdata.getContcatLastName());
		type( By.name("address"), contactdata.getContactAddress());
		type( By.name("mobile"), contactdata.getContactPhone());
		type( By.name("email"), contactdata.getContactEmail());
	}
}