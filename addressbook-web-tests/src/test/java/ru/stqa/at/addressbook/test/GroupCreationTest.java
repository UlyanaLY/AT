package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getGroupHelper().goToGroupPage();
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().createAGroup(new GroupData("friends", null, null));
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before + 1);
	}
}