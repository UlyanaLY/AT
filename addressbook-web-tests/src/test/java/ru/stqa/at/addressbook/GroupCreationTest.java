package ru.stqa.at.addressbook;

import org.testng.annotations.*;

public class GroupCreationTest extends TestBase{

	@Test
	public void testGroupCreation() throws Exception {
		goToGroupPage();
		initGroupCreation();
		fillGroupForm(new GroupData("friends", "friends header", "friends footer"));
		submitGroupForm();
		goToGroupPage();
		checkCreatedGroup();
	}
}