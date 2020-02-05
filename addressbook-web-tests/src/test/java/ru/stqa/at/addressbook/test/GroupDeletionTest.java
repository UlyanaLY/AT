package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("test1"));
		}
	}

	@Test
	public void testGroupDeletion() {
		app.goTo().groupPage();
		Groups before = app.group().all();
		GroupData deletedGroup = before.iterator().next();
		app.group().delete(deletedGroup);
		app.goTo().groupPage();
		Groups after = app.group().all();
		Assert.assertEquals(after.size(), before.size() - 1);

		assertThat(after, equalTo(before.without(deletedGroup)));
	}
}