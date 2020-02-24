package ru.stqa.at.addressbook.test;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.Contacts;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {
	@DataProvider
	public Iterator<Object[]> validContactsFromXml() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				xml += line;
				line = reader.readLine();
			}
			XStream xstream = new XStream();
			xstream.processAnnotations(ContactData.class);
			List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
			return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
		}
	}

	@Test(dataProvider = "validContactsFromXml")
	public void testContactCreation(ContactData contact) {
		app.goTo().groupPage();

		if (app.db().groups().size() == 0) {
			String dateString = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
			app.group().create(new GroupData().withName("test" + dateString
			));
		}
		Groups groups = app.db().groups();
		app.goTo().contactPage();
		Contacts before = app.db().contacts();
		ContactData newContact = contact.inGroup(groups.iterator().next());
		app.contact().create(newContact, true);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
		verifyContactListInUi();
	}
}