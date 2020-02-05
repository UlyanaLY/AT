package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTest extends TestBase {

	@Test
	public void testGroupCreation() {
		app.goTo().groupPage();
		Groups before = app.group().all();
		GroupData group = new GroupData().withName("friends1");
		app.group().create(group);
		app.goTo().groupPage();
		Groups after = app.group().all();
		assertThat(after.size(), equalTo(before.size() + 1));

		before.add(group);
		assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}
}