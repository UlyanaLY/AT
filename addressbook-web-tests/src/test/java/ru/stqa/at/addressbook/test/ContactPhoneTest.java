package ru.stqa.at.addressbook.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTest extends TestBase {

	@Test(enabled=true)
	public void testContactPhones() {
		app.goTo().contactPage();
		ContactData contact = app.contact().allContacts().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
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


	@Test
	public void SearchForWorkDir(){
		File currentDir = new File(".");
	}
}
