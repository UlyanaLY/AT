package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions(){
		app.goTo().contactPage();
		if (app.contact().list().size() == 0) {
			app.contact().create(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
			app.goTo().contactPage();
		}
	}

	@Test(enabled=true)
	public void testContactDeletion() {
		List<ContactData> before = app.contact().list();
		app.contact().checkCreatedContact(before.size() - 1);
		app.contact().deleteContact();
		app.goTo().contactPage();
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}