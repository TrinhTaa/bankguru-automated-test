package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.NewAccountUI;

public class NewAccountPage extends BasePage {
	WebDriver driver;
	
	NewAccountPage(WebDriver driver){
		this.driver = driver;
	}

	public void verifyHeadingVisible() {
		waitForElementVisible(driver, NewAccountUI.Heading_text);		
	}

	public void inputDataToTextboxByName(String name, String value) {
		scrollToElementOnDown(driver, NewAccountUI.Dynamic_input, name);
		waitForElementVisible(driver, NewAccountUI.Dynamic_input, name);
		sendkeyToElement(driver, NewAccountUI.Dynamic_input, value, name);
		
	}

	public void sendTabToTextboxByName(String name) {
		pressKeyToElement(driver, NewAccountUI.Dynamic_input, Keys.TAB, name);
		
	}

	public String getMessageOfFieldByName(String name) {
		waitForElementVisible(driver, NewAccountUI.Dynamic_message, name);
		return null;
	}
	
	
	
	

}
