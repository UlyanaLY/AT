package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {

	@Test
	public void testGroupModification() {
		app.getGroupHelper().goToGroupPage();

		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createAGroup(new GroupData("test1", "test_header", "test_footer"));
		}
		app.getGroupHelper().goToGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();;
		app.getGroupHelper().checkCreatedGroup(0);
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupData("friends", "test2_header", "test2_footer"));
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup(0);
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
	}
}