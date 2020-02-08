package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

	private boolean creation;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void deleteContact() {
		click(By.xpath("//input[@value='Delete']"));
		if (isAlertPresent() == true) {
			wd.switchTo().alert().accept();
		}
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteContact();
		contactCache = null;
	}

	public void fillContactForm(ContactData contactdata, boolean creation) {
		type(By.name("firstname"), contactdata.getContactName());
		type(By.name("lastname"), contactdata.getContactLastName());
		type(By.name("address"), contactdata.getContactAddress());
		type(By.name("mobile"), contactdata.getHomePhone());
		type(By.name("email"), contactdata.getEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactdata.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
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
		fillContactForm(new ContactData().withName("Ivan").withLastName("Ivanov")
						.withAddress("099038, Crimea, Simferopol, Lenina 26-a").withHomePhone("898978909889")
						.withEmail("testing1@gmail.com").withGroup("friends"), isCreated);
		submitContactForm();
		contactCache = null;
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModificationById(contact.getId());
		fillContactForm(contact, false);
		submitContactModification();
		contactCache = null;
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public ContactData infoFromEditForm(ContactData contact) {
		initContactModificationById(contact.getId());
		String contactName = wd.findElement(By.name("firstname")).getAttribute("value");
		String contactLastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		wd.navigate().back();

		return new ContactData().withId(contact.getId()).withName(contactName).withLastName(contactLastName)
						.withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

	}

//	private void initContactModificationById (int id){
//		WebElement checkbox = wd.findElement(By.cssSelector(String.format("a[href='edit.php?Id=%s']", id))).click();
//
//	}

	public List<ContactData> list() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element : elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();

			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			ContactData contact = new ContactData().withId(id).withName(contactName).withLastName(contactLastName);
			contacts.add(contact);
		}
		return contacts;
	}

	public Set<ContactData> allContacts() {
		Set<ContactData> contacts = new HashSet<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element : elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String contactAddress = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
			String contactEmail = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
			String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			contacts.add(new ContactData().withId(id).withName(contactName).withLastName(contactLastName).withAllPhones(allPhones));
		}
		return contacts;
	}

	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null) {
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));

		for (WebElement element : elements) {
			String contactName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String contactLastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();

			int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
			ContactData contact = new ContactData().withId(id).withName(contactName).withLastName(contactLastName);
			contactCache.add(contact);
		}
		return new Contacts(contactCache);
	}
}