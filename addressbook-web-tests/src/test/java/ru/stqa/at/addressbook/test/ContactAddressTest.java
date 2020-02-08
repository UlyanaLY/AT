package ru.stqa.at.addressbook.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

public class ContactAddressTest extends TestBase {
	@Test
	public void testContactAddress() {
		app.goTo().contactPage();
		ContactData contact = app.contact().allContacts().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(cleaned(contact.getContactAddress()), equalTo(cleaned(contactInfoFromEditForm.getContactAddress())));
	}

	public static String cleaned(String address) {
		return address.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
