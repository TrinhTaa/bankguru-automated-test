package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.DashBoardUI;

public class DashBoardPage extends BasePage {
	WebDriver driver;
	
	DashBoardPage(WebDriver driver){
		this.driver = driver;
	}

	public void clickToMenuByText(String text) {
		waitForElementClickable(driver, DashBoardUI.Dynamic_menu, text);
		clickToElement(driver, DashBoardUI.Dynamic_menu, text);
	}

}
