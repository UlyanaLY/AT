package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getGroupHelper().goToGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() + 1);
	}
}