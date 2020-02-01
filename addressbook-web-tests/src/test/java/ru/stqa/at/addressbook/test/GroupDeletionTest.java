package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.getGroupHelper().goToGroupPage();
		if (!app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().checkCreatedGroup(0);
		app.getGroupHelper().deleteGroup();
		app.getGroupHelper().goToGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}