package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;

public class GroupDeletionTest extends TestBase {

	@Test
	public void testUntitledTestCase() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().checkCreatedGroup();
		app.getGroupHelper().deleteGroup();
		app.getNavigationHelper().goToGroupPage();
	}
}