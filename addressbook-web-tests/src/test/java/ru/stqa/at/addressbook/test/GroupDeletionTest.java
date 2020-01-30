package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.getGroupHelper().goToGroupPage();
		if (!app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		}
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().checkCreatedGroup();
		app.getGroupHelper().deleteGroup();
		app.getGroupHelper().goToGroupPage();
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before - 1);
	}
}