package ru.stqa.at.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupDeletionTest extends TestBase{

	@Test
	public void testUntitledTestCase() throws Exception {
		goToGroupPage();
		checkCreatedGroup();
		deleteGroup();
		goToGroupPage();
	}
}
