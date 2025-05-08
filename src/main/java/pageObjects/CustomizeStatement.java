package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.CustomizePageUI;

public class CustomizeStatement extends BasePage {
	WebDriver driver;
	
	CustomizeStatement(WebDriver driver){
		this.driver =  driver;
	}

	public void inputTextToElementByName(String name, String text) {
		scrollToElementOnDown(driver, CustomizePageUI.Dynamic_input, name);
		waitForElementVisible(driver, CustomizePageUI.Dynamic_input, name);
		sendkeyToElement(driver, CustomizePageUI.Dynamic_input, text, name);
	}

	public void sendTabToElementByName(String name) {
		pressKeyToElement(driver, CustomizePageUI.Dynamic_input, Keys.TAB, name);
		
	}

	public String getWarningMessageByName(String name) {
		scrollToElementOnDown(driver, CustomizePageUI.Dynamic_input, name);
		waitForElementVisible(driver, CustomizePageUI.Dynamic_input, name);
		String message = getElementText(driver, CustomizePageUI.Dynamic_Message, name);
		return message;
	}
	

}
