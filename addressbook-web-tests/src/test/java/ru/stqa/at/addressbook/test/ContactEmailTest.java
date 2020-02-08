package ru.stqa.at.addressbook.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailTest extends TestBase {

	@Test
	public void testContactPhones() {
		app.goTo().contactPage();
		ContactData contact = app.contact().allContacts().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
	}

	private String mergeEmails(ContactData contact) {
		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
						.stream().filter((s) -> !s.equals(""))
						.map(ContactPhoneTest::cleaned)
						.collect(Collectors.joining("\n"));
	}

	public static String cleaned(String email) {
		return email.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
