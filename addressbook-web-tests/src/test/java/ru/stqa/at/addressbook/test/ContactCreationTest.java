package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
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
		ContactData contact = new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com", "friends");
		app.getContactHelper().createAContact(contact, true);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super  ContactData> byId = (g1, g2) ->  Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
	}
}