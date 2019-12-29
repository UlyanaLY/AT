package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getNavigationHelper().goToCreateContactPage();
		app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com"));
		app.getContactHelper().submitContactForm();
		app.getNavigationHelper().goToHomePage();
		app.getContactHelper().checkCreatedContact();
	}
}