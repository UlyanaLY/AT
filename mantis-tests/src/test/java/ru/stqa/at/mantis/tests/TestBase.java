package ru.stqa.at.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.at.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

	protected static final ApplicationManager app
					= new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws IOException {

		app.init();
		app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
	}

	public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
		MantisConnectPortType mc = app.soap().getMantisConnect();
		IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));

		ObjectRef status = issue.getStatus();

		return !status.getName().equals("resolved");
	}

	public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
		if (isIssueOpen(issueId)) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws IOException {
		app.ftp().restore("config_inc.php.back", "config_inc.php");
		app.stop();
	}
}