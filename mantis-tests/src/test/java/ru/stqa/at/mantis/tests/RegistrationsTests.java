package ru.stqa.at.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationsTests extends TestBase {


	@Test
	public void testRegistration () {
		app.registration().start("user1", "user1@localhost.localdomain");
	}
}
