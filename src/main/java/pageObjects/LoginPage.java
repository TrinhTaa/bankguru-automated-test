package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.LoginUI;

public class LoginPage extends BasePage {
	WebDriver driver;
	
	LoginPage (WebDriver driver){
		this.driver = driver;
	}

	public void InputID(String textValue) {
		waitForElementVisible(driver, LoginUI.UserId_input);
		sendkeyToElement(driver, LoginUI.UserId_input, textValue);
	}
	
	public void InputPW(String pass) {
		waitForElementVisible(driver, LoginUI.Password_input);
		sendkeyToElement(driver, LoginUI.Password_input, pass);
	}
	
	public void clickToLoginButton(WebDriver driver) {
		waitForElementClickable(driver, LoginUI.Login_button);
		clickToElement(driver, LoginUI.Login_button);
	}

	

	
	
	

}
