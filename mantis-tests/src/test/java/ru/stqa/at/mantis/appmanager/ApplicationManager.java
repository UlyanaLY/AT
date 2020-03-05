package ru.stqa.at.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
	private final Properties properties;
	private WebDriver wd;
	private String browser;
	private RegistrationHelper registrationHelper;
	private UsersHelper usersHelper;
	private FtpHelper ftp;
	private MailHelper mailHelper;
	private DbHelper dbHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if( wd != null ) {
			wd.quit();
		}
	}

	public HttpSession newSession() {
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if(registrationHelper==null){
			registrationHelper = new RegistrationHelper(this);
		}	
		return registrationHelper;
	}

	public FtpHelper ftp() {
		if (ftp == null) {
			ftp = new FtpHelper(this);
		}

		return ftp;
	}

	public WebDriver getDriver() {
		if( wd == null ) {
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
		}
		return wd;
	}

	public MailHelper mail () {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}

	public UsersHelper usersHelper() {
		if (usersHelper == null) {
			usersHelper = new UsersHelper(this);
		}
		return usersHelper;
	}

	public DbHelper db() {
		if (dbHelper ==null){
			dbHelper = new DbHelper(this);
		}
		return dbHelper;
	}
}