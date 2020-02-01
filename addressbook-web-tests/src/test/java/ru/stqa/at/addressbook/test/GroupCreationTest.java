package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getGroupHelper().goToGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();
		GroupData group = new GroupData("friends1", null, null);
		app.getGroupHelper().createAGroup(group);
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}