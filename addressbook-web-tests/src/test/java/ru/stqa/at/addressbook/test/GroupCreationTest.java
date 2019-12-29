package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.goToGroupPage();
		app.initGroupCreation();
		app.fillGroupForm(new GroupData("friends", "friends header", "friends footer"));
		app.submitGroupForm();
		app.goToGroupPage();
		app.checkCreatedGroup();
	}
}