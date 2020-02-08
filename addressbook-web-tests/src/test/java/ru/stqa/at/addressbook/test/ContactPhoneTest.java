package ru.stqa.at.addressbook.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTest extends TestBase {

	@Test
	public void testContactPhones() {
		app.goTo().contactPage();
		ContactData contact = app.contact().allContacts().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		String gol = contact.getAllPhones();
		String mf = mergePhones(contactInfoFromEditForm);
		assertThat(gol, equalTo(mf));
	}

	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
						.stream().filter((s) -> !s.equals(""))
						.map(ContactPhoneTest::cleaned)
						.collect(Collectors.joining("\n"));
	}

	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
