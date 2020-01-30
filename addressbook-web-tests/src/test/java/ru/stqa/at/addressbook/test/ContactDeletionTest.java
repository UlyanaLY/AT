package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getContactHelper().goToHomePage();
		int before = app.getContactHelper().getContactCount();
		if (!app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().goToCreateContactPage();
			app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
		} else {
			app.getContactHelper().checkCreatedContact();
		}
		app.getContactHelper().deleteContact();
		app.getContactHelper().goToHomePage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before - 1);
	}
}