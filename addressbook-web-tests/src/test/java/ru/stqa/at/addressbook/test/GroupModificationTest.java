package ru.stqa.at.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.at.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

	@Test
	public void testGroupModification() throws Exception {
		app.getGroupHelper().goToGroupPage();

		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper().createAGroup(new GroupData("test1", "test_header", "test_footer"));
		}
		app.getGroupHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupData("test2", "test2_header", "test2_footer"));
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
	}
}
