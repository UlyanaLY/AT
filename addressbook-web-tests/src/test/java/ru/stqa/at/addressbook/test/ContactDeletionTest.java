package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

	@Test(enabled=true)
	public void testContactDeletion() {
		app.getContactHelper().goToHomePage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().goToCreateContactPage();
			app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().checkCreatedContact(before.size() - 1);
		app.getContactHelper().deleteContact();
		app.getContactHelper().goToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}