package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomeUI;

public class HomePO extends BasePage{
	WebDriver driver;
	
	HomePO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToBankProject(WebDriver driver) {
		waitForElementClickable(driver, HomeUI.BankProject_dropdown);
		clickToElement(driver, HomeUI.BankProject_dropdown);		
	}

}
