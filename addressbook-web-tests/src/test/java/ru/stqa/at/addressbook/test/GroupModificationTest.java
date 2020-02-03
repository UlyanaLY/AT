package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.getGroupHelper().goToGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createAGroup(new GroupData("test1", "test_header", "test_footer"));
		}
	}

	@Test
	public void testGroupModification() {

		List<GroupData> before = app.getGroupHelper().getGroupList();
		GroupData group = new GroupData(before.get(before.size() - 1).getId(), "friends", "test2_header", "test2_footer");
		int index = before.size() - 1;
		app.getGroupHelper().modifyGroup(group, index);
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(index);
		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}