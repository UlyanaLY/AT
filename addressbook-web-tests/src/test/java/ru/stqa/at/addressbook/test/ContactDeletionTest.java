package ru.stqa.at.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

	@Test
	public void testContactDeletion() throws Exception {
		app.getNavigationHelper().goToHomePage();
		app.getContactHelper().checkCreatedContact();
		app.getContactHelper().deleteContact();
		app.getNavigationHelper().goToHomePage();
	}
}