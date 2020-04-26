package ru.stqa.at.addressbook.test;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.at.addressbook.appmanager.ApplicationManager;
import ru.stqa.at.addressbook.model.ContactData;
import ru.stqa.at.addressbook.model.Contacts;
import ru.stqa.at.addressbook.model.GroupData;
import ru.stqa.at.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;


public class TestBase {
	Logger logger = LoggerFactory.getLogger(TestBase.class);
	protected static final ApplicationManager app
					= new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws IOException {
		app.init();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		app.stop();
	}

	@BeforeMethod
	public void logTestStart(Method m, Object[] p) {
		logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
	}

	@AfterMethod(alwaysRun = true)
	public void logTestStop(Method m) {
		logger.info("End test " + m.getName());
	}

	public void verifyGroupListInUi() {
		if (Boolean.getBoolean("verifyUi")) {
			Groups dbGroups = app.db().groups();
			Groups uiGroups = app.group().all();
			MatcherAssert.assertThat(uiGroups, equalTo(dbGroups.stream()
							.map((g) -> new GroupData().withId(g.getId()).withName(g.getGroupName()))
							.collect(Collectors.toSet())));
			System.out.println("Ui is verified");
		}
	}

	public void verifyContactListInUi() {
		if (Boolean.getBoolean("verifyUi")) {
			Contacts dbContacts = app.db().contacts();
			Contacts uiContacts = app.contact().all();
			MatcherAssert.assertThat(uiContacts, equalTo(dbContacts.stream()
							.map((g) -> new ContactData().withId(g.getId()).withName(g.getContactName()).withLastName(g.getContactLastName()))
							.collect(Collectors.toSet())));
			System.out.println("Ui is verified in contacts");
		}
	}
}