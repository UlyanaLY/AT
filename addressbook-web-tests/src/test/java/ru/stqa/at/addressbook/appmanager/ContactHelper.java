package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

	private boolean creation;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void selectContactById(int id) {
			wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
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

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteContact();
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

	public void initContactModificationById(int id) {
		wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
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

	public void create(ContactData contactData, boolean isCreated) {
		click(By.linkText("add new"));
	  fillContactForm(new ContactData().withName("Ivan").withLastName( "Ivanov")
						.withAddress( "099038, Crimea, Simferopol, Lenina 26-a").withPhone("898978909889")
						.withEmail("testing1@gmail.com").withGroup("friends"), isCreated);
		submitContactForm();
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModificationById(contact.getId());
		fillContactForm(contact, false);
		submitContactModification();
	}

	public List<ContactData> list() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element: elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
//			String contactAddress = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
//			String contactEmail = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
//			String contactPhone = element.findElement(By.cssSelector("td:nth-child(6)")).getText();

			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			ContactData contact = new ContactData().withId(id).withName(contactName).withLastName(contactLastName);
			contacts.add(contact);
		}
		return contacts;
	}

	public Set<ContactData> all() {
		Set<ContactData> contacts = new HashSet<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element: elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();

			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			ContactData contact = new ContactData().withId(id).withName(contactName).withLastName(contactLastName);
			contacts.add(contact);
		}
		return contacts;
	}


}