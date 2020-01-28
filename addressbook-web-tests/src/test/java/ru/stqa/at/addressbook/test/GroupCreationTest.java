package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getGroupHelper().goToGroupPage();
		app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		app.getSessionHelper().logout();
	}
}