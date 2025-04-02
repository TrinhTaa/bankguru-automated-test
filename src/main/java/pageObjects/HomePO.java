package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomeUI;

public class HomePO extends BasePage{
	WebDriver driver;
	
	HomePO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAccountButton(WebDriver driver) {
		waitForElementClickable(driver, HomeUI.Home_button_Account);
		clickToElement(driver, HomeUI.Home_button_Account);
		
	}

	public void clickToRegisterMenu(WebDriver driver) {
		waitForElementClickable(driver, HomeUI.Home_menu_Register);
		clickToElement(driver, HomeUI.Home_menu_Register);
		
	}
	
	public void clickToMyAccountMenu(WebDriver driver) {
		waitForElementClickable(driver, HomeUI.Home_menu_MyAccount);
		clickToElement(driver, HomeUI.Home_menu_MyAccount);
		
	}

}
