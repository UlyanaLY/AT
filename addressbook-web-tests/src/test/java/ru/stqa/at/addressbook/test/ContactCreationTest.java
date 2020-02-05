package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

	@Test(enabled = true)
	public void testContactCreation() {
		app.goTo().groupPage();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("friends"));
		}
		app.goTo().contactPage();
		Set<ContactData> before = app.contact().all();
		ContactData contact = new ContactData().withName("Ivan").withLastName("Ivanov").withAddress("099038, Crimea, Simferopol, Lenina 26-a")
						.withPhone("898978909889").withEmail("testing1@gmail.com").withGroup("friends");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		Set<ContactData> after = app.contact().all();
		assertThat(after.size(), equalTo(before.size() + 1));

		contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
		before.add(contact);
		assertThat(after, equalTo(before));
	}
}