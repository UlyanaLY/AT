package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getContactHelper().goToHomePage();

		if (!app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().goToCreateContactPage();
			app.getContactHelper().createAContact(new ContactData("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
							"898978909889", "testing1@gmail.com", "friends"), true);
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().initContactModification();
		ContactData contact = new ContactData("Ivan1", "Ivanov1", "099031, Crimea, Kerch, Lenina 26-a",
						"898978909881", "testing123@gmail.com", null);
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().goToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		before.remove(before.size() - 1);
		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) ->  Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}