package ru.stqa.at.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTest extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("test1"));
			app.goTo().groupPage();
		}
	}

	@Test
	public void testGroupModification() {
		Groups before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
						.withId(modifiedGroup.getId()).withName("friends").withHeader("test2_header").withFooter("test2_footer");
		app.group().modify(group);
		app.goTo().groupPage();
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.group().all();
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}
}