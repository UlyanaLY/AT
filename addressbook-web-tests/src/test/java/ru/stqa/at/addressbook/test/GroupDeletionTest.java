package ru.stqa.at.addressbook.test;

import org.testng.annotations.*;

public class GroupDeletionTest extends TestBase {

	@Test
	public void testUntitledTestCase() throws Exception {
		app.goToGroupPage();
		app.checkCreatedGroup();
		app.deleteGroup();
		app.goToGroupPage();
	}
}