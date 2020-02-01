package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getContactHelper().goToHomePage();

		if (!app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().goToCreateContactPage();
			app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
		} else {
			app.getContactHelper().checkCreatedContact();
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("Ivan1", "Ivanov1", "099031, Crimea, Kerch, Lenina 26-a",
						"898978909881", "testing123@gmail.com", null), false);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().goToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();

		Assert.assertEquals(after.size(), before.size());
	}
}