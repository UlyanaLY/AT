package ru.stqa.at.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.at.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginTests extends TestBase {
	@Test
	public void testLogin() throws IOException {
		HttpSession session = app.newSession();
		assertTrue(session.login("administrator", "root"));
		assertTrue(session.isLoggedInAs("administrator"));
	}
}