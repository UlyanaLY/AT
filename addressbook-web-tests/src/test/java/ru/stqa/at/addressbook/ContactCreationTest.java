package ru.stqa.at.addressbook;

import org.testng.annotations.*;

public class ContactCreationTest extends TestBase{

	@Test
	public void testContactCreation() throws Exception {
		goToCreateContactPage();
		fillContactForm(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com"));
		submitContactForm();
		goToHomePage();
		checkCreatedContact();
	}
}