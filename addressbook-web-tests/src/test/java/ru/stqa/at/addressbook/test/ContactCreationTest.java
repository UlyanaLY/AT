package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test(enabled = true)
	public void testContactCreation() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData().withName("friends"));
		}
		app.goTo().contactPage();
		List<ContactData> before = app.contact().list();
		ContactData contact = new ContactData().withName("Ivan").withLastName("Ivanov").withAddress("099038, Crimea, Simferopol, Lenina 26-a")
						.withPhone("898978909889").withEmail("testing1@gmail.com").withGroup("friends");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}