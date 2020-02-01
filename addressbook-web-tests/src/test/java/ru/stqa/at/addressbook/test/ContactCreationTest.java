package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test
	public void testContactCreation() {
		app.getContactHelper().goToHomePage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getGroupHelper().goToGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		}
		app.getNavigationHelper().goToCreateContactPage();
		app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com", "friends"), true);
		List<ContactData> after = app.getContactHelper().getContactList();;
		Assert.assertEquals(after.size(), before.size() + 1);
	}
}