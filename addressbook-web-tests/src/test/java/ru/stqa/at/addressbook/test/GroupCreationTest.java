package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().initGroupCreation();
		app.getGroupHelper().fillGroupForm(new GroupData("friends", null, null));
		app.getGroupHelper().submitGroupForm();
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
	}
}