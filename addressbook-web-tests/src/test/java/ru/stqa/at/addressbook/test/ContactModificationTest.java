package ru.stqa.at.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() throws Exception {
		app.getContactHelper().goToHomePage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().goToCreateContactPage();
			app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
		} else {
			app.getContactHelper().checkCreatedContact();
		}
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("Ivan1", "Ivanov1", "099031, Crimea, Kerch, Lenina 26-a",
						"898978909881", "testing123@gmail.com", null), false);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().goToHomePage();
	}
}