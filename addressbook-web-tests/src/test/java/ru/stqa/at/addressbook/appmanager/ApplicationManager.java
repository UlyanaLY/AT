package ru.stqa.at.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
	protected WebDriver wd;

	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private String browser;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

		if (browser.equals(BrowserType.CHROME)) {
			System.setProperty("webdriver.chrome.driver", properties.getProperty("web.chromeDriverPath"));
			wd = new ChromeDriver();
		} else if (browser.equals(BrowserType.IE)) {
			System.setProperty("webdriver.ie.driver", properties.getProperty("web.fireFoxDriverPath"));
			wd = new InternetExplorerDriver();
			wd.manage().window().maximize();
		} else if (browser.equals(BrowserType.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", properties.getProperty("web.ieDriverPath"));
			wd = new FirefoxDriver();
		}

		wd.get(properties.getProperty("web.baseUrl"));
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper group() {
		return groupHelper;
	}
	public NavigationHelper goTo() {
		return navigationHelper;
	}
	public ContactHelper contact() {
		return contactHelper;
	}
	public SessionHelper getSessionHelper() {
		return sessionHelper;
	}
}