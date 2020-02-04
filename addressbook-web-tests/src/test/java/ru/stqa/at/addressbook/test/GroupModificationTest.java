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
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData("test1", "test_header", "test_footer"));
			app.goTo().groupPage();
		}
	}

	@Test
	public void testGroupModification() {

		List<GroupData> before = app.group().list();
		GroupData group = new GroupData(before.get(before.size() - 1).getId(), "friends", "test2_header", "test2_footer");
		int index = before.size() - 1;
		app.group().modify(group, index);
		app.goTo().groupPage();
		List<GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), before.size());

		before.remove(index);
		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}