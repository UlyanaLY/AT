package ru.stqa.at.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupData("friends", "friends header", "friends footer"));
		app.getGroupHelper().submitGroupModification();
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
	}
}
