package ru.stqa.at.mantis.appmanager;

import org.openqa.selenium.By;

public class UsersHelper extends HelperBase {

	public UsersHelper(ApplicationManager app) {
		super(app);
	}

	public void start(String username, String password) {
		wd.get(app.getProperty("web.baseUrl") + "login_page.php");
		type(By.name("username"), username);
		click(By.cssSelector("input[value='Войти']"));
		type(By.name("password"), password);
		click(By.cssSelector("input[value='Войти']"));
	}

	public void resetPassword(String userId) {
		wd.get(app.getProperty("web.baseUrl") + "manage_user_edit_page.php?user_id=" + userId);
		click(By.cssSelector("input[value='Сбросить пароль']"));
	}

	public void changeAccount(String confirmationLink, String username) {
		wd.get(confirmationLink);
		type(By.name("realname"), username);
		type(By.name("password"), "newpwd");
		type(By.name("password_confirm"), "newpwd");
		click(By.xpath("//span[contains(text(), 'Изменить')]"));
	}


}