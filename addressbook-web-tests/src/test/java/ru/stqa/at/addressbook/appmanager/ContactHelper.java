package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

	private boolean creation;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void checkCreatedContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
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

	public void initContactModification(int index) {
		wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
	}
	public void checkCreatedGroup(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
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
	}

	public void goToHomePage() {
		if (isElementPresent(By.id("maintainable"))) {
			return;
		}
		click(By.linkText("home"));
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element: elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
//			String contactAddress = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
//			String contactEmail = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
//			String contactPhone = element.findElement(By.cssSelector("td:nth-child(6)")).getText();

			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			ContactData contact = new ContactData(id, contactName, contactLastName, null, null, null, null);
			contacts.add(contact);
		}
		return contacts;
	}
}