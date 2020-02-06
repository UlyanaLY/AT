package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		;
		if (app.contact().list().size() == 0) {
			app.contact().create(new ContactData().withName("Ivan").withLastName("Ivanov").withAddress("099038, Crimea, Simferopol, Lenina 26-a")
							.withPhone("898978909889").withEmail("testing1@gmail.com").withGroup("friends"), true);
			app.goTo().contactPage();
		}
	}

	@Test
	public void testContactModification() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Ivan").withLastName("Ivanov2")
						.withAddress("099031, Crimea, Kerch, Lenina 26-a").withPhone("898978909881").withEmail("testing123@gmail.com");
		app.contact().modify( contact);
		app.goTo().contactPage();
		Contacts after = app.contact().all();
		Assert.assertEquals(after.size(), before.size());

		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}