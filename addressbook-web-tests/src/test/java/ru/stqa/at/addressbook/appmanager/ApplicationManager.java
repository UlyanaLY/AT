package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

	protected WebDriver wd;

	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private String browser;

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public void init() {

		if (browser.equals(BrowserType.CHROME)) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Documents\\GitHub\\drivers\\chromedriver.exe");
			wd = new ChromeDriver();
		} else if (browser.equals(BrowserType.IE)) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\PC\\Documents\\GitHub\\drivers\\IEDriverServer.exe");
			wd = new InternetExplorerDriver();
			wd.manage().window().maximize();
		} else if (browser.equals(BrowserType.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\PC\\Documents\\GitHub\\drivers\\geckodriver.exe");
			wd = new FirefoxDriver();
		}

		wd.get("http://localhost/addressbook/");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper getGroupHelper() {
		return groupHelper;
	}
	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}
	public ContactHelper getContactHelper() {
		return contactHelper;
	}
	public SessionHelper getSessionHelper() {
		return sessionHelper;
	}
}