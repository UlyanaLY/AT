package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		if (app.contact().list().size() == 0) {
			app.contact().create(new ContactData().withName("Ivan").withLastName("Ivanov")
							.withAddress("099038, Crimea, Simferopol, Lenina 26-a").withPhone("898978909889")
							.withEmail("testing1@gmail.com").withGroup("friends"), true);
			app.goTo().contactPage();
		}
	}

	@Test(enabled = true)
	public void testContactDeletion() {
		Set<ContactData> before = app.contact().all();
		ContactData deletedContact = before.iterator().next();
		//app.contact().selectContact(before.size() - 1);
		app.contact().delete(deletedContact);
		app.goTo().contactPage();
		Set<ContactData> after = app.contact().all();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(deletedContact);
		Assert.assertEquals(before, after);
	}
}