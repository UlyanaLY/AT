package ru.stqa.at.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ContactCreationTest {
	private WebDriver wd;

	@BeforeClass(alwaysRun = true)
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

	@Test
	public void testContactCreation() throws Exception {
		goToCreateContactPage();
		fillContactForm(new Contactdata("Ivan", "Ivanov", "099038, Crimea, Simferopol, Lenina 26-a",
						"898978909889", "testing1@gmail.com"));
		submitContactForm();
		goToHomePage();
		checkCreatedContact();
	}

	private void checkCreatedContact() {
		wd.findElement(By.id("3")).click();
	}

	private void goToHomePage() {
		wd.findElement(By.linkText("home")).click();
	}

	private void submitContactForm() {
		wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	}

	private void fillContactForm(Contactdata contactdata) {
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

	private void goToCreateContactPage() {
		wd.findElement(By.linkText("add new")).click();
	}

	@AfterClass(alwaysRun = true)
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
