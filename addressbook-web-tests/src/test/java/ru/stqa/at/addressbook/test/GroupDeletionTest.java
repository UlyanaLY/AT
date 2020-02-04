package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData("test1", "test_header", "test_footer"));
		}
	}

	@Test
	public void testGroupDeletion() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData("friends", null, null));
		}
		List<GroupData> before = app.group().list();
		int index = before.size() - 1;
		app.group().delete(index);
		app.goTo().groupPage();
		List<GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(index);
		Assert.assertEquals(before, after);
	}
}