package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;

import java.security.acl.Group;
import java.util.HashSet;
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
		app.getGroupHelper().checkCreatedGroup(before.size() - 1);
		app.getGroupHelper().initGroupModification();
		GroupData group = new GroupData( before.get(before.size() - 1).getId(),"friends", "test2_header", "test2_footer");
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup(before.size() - 1);
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size() - 1);
		before.add(group);
		Assert.assertEquals(new HashSet<Object> (before),new HashSet<Object> (after) );
	}
}