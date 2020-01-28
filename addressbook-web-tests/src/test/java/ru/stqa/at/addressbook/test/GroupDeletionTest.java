package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.getGroupHelper().goToGroupPage();
		if (!app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		}
		app.getGroupHelper().checkCreatedGroup();
		app.getGroupHelper().deleteGroup();
		app.getGroupHelper().goToGroupPage();
		app.getSessionHelper().logout();
	}
}