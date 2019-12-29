package ru.stqa.at.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
	protected WebDriver wd;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Documents\\GitHub\\drivers\\chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		login("admin", "secret");
	}

	private void login(String username, String password) {
		wd.findElement(By.name("user")).click();
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys(username);
		wd.findElement(By.id("LoginForm")).click();
		wd.findElement(By.name("pass")).click();
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys(password);
		wd.findElement(By.xpath("//input[@value='Login']")).click();
	}

	protected void goToHomePage() {
		wd.findElement(By.linkText("home")).click();
	}

	//methods for group
	protected void submitGroupForm() {
		wd.findElement(By.name("submit")).click();
	}

	protected void fillGroupForm(GroupData groupData) {
		wd.findElement(By.name("group_name")).click();
		wd.findElement(By.name("group_name")).clear();
		wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
		wd.findElement(By.name("group_header")).click();
		wd.findElement(By.name("group_header")).clear();
		wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
		wd.findElement(By.name("group_footer")).click();
		wd.findElement(By.name("group_footer")).clear();
		wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
	}

	protected void goToGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}

	protected void initGroupCreation() {
		wd.findElement(By.name("new")).click();
	}

	void checkCreatedGroup() {
		wd.findElement(By.name("selected[]")).click();
	}

	protected void deleteGroup() {
		wd.findElement(By.name("delete")).click();
	}

	//methods for contacts
	protected void checkCreatedContact() {
		wd.findElement(By.id("3")).click();
	}

	protected void submitContactForm() {
		wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	}

	protected void fillContactForm(ContactData contactdata) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(contactdata.getContactName());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(contactdata.getContcatLastName());
		wd.findElement(By.name("address")).click();
		wd.findElement(By.name("address")).clear();
		wd.findElement(By.name("address")).sendKeys(contactdata.getContactAddress());
		wd.findElement(By.name("mobile")).click();
		wd.findElement(By.name("mobile")).clear();
		wd.findElement(By.name("mobile")).sendKeys(contactdata.getContactPhone());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(contactdata.getContactEmail());
	}

	protected void goToCreateContactPage() {
		wd.findElement(By.linkText("add new")).click();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		wd.findElement(By.linkText("Logout")).click();
		wd.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}
