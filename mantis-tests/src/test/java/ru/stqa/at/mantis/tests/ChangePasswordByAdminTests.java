package ru.stqa.at.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.at.mantis.appmanager.HttpSession;
import ru.stqa.at.mantis.model.MailMessage;
import ru.stqa.at.mantis.model.UserData;
import ru.stqa.at.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordByAdminTests extends TestBase {
	@BeforeMethod
	public void startMailServer() {
		app.mail().start();
	}

	@Test
	public void ChangePasswordTests() throws IOException, InterruptedException {
		Users users = app.db().users();
		UserData user = users.iterator().next();
		System.out.println(user.getId());
		app.usersHelper().start("administrator", "root");
		app.usersHelper().resetPassword(Integer.toString(user.getId()));

		List<MailMessage> mailMessages = app.mail().waitForMail(1, 50000);
		String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
		System.out.println(confirmationLink);
		app.usersHelper().changeAccount(confirmationLink, user.getName());
		Thread.sleep(5000);
		HttpSession session = app.newSession();

		assertTrue(session.login(user.getName(), "newpwd"));
		assertTrue(session.isLoggedInAs(user.getName()));
	}

	private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}

	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}
}
