package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

	private boolean creation;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void checkCreatedContact() {
		click(By.name("selected[]"));
	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void deleteContact() {
		click(By.xpath("//input[@value='Delete']"));
		if( isAlertPresent()==true) {
			wd.switchTo().alert().accept();
		};
	}

	public void fillContactForm(ContactData contactdata, boolean creation) {
		type( By.name("firstname"), contactdata.getContactName());
		type( By.name("lastname"), contactdata.getContactLastName());
		type( By.name("address"), contactdata.getContactAddress());
		type( By.name("mobile"), contactdata.getContactPhone());
		type( By.name("email"), contactdata.getContactEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactdata.getGroup());
		} else {
			Assert.assertFalse(	isElementPresent(By.name("new_group")));
		}
	}

	public void initContactModification() {
		click(By.xpath("//img[@alt='Edit']"));
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public void createAContact(ContactData contactData, boolean isCreated) {
	  fillContactForm(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com", "friends"), isCreated);
		submitContactForm();
		goToHomePage();
		checkCreatedContact();
	}

	public void goToHomePage() {
		if (isElementPresent(By.id("maintainable"))) {
			return;
		}
		click(By.linkText("home"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
}