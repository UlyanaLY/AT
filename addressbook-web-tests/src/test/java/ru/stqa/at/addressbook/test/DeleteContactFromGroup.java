package ru.stqa.at.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.Contacts;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		if (app.db().contacts().size() == 0) {
			String dateString = new SimpleDateFormat("MM-dd-hh-mm-ss").format(new Date());
			app.contact().create(new ContactData().withName("Ivan_" + dateString)
							.withLastName("Ivanov").withAddress("099038, Crimea, Simferopol, Lenina")
							.withHomePhone("89789098900").withEmail("testing@gmail.com")
							.withPhoto("src/test/resources/image.jpg"), true);
		}
		if (app.db().contacts().size() == 0) {
			app.goTo().groupPage();
			String dateString = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
			app.group().create(new GroupData().withName("test" + dateString
			));
		}
	}

	@Test
	public void testDeleteContactFromGroup() {
		app.goTo().contactPage();


		Contacts beforeC = app.db().contacts();
		Groups beforeG = app.db().groups();
		ContactData contactToGroup = beforeC.iterator().next();
		int contactId = contactToGroup.getId();
		if (contactToGroup.getGroups().size() == 0) {
			app.contact().addContactInGroup(contactToGroup, beforeG.iterator().next());
		}
		ContactData newContact = app.db().GetContactDataById(contactId);
		Groups contactGroupsBefore = newContact.getGroups();

		app.goTo().contactPage();
		app.contact().deleteContactFromGroup(contactToGroup, newContact.getGroups().iterator().next());

		Groups contactGroupsAfter = app.db().GetContactDataById(contactId).getGroups();
		assertThat(contactGroupsBefore.size()-1, equalTo(contactGroupsAfter.size()));
	}
}
