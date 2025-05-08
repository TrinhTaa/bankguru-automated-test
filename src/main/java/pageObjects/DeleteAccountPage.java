package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.DeleteAccountUI;

public class DeleteAccountPage extends BasePage {
	WebDriver driver;
	
	DeleteAccountPage(WebDriver driver){
		this.driver = driver;
	}

	public void inputTextToAccountNumber(String text) {
		waitForElementVisible(driver, DeleteAccountUI.AccountNumber_input);
		sendkeyToElement(driver, DeleteAccountUI.AccountNumber_input, text);
	}

	public String getWarningMessage() {
		waitForElementVisible(driver, DeleteAccountUI.WarningMessage_text);
		String message = getElementText(driver, DeleteAccountUI.WarningMessage_text);
		return message;
	}

	public void SendTabToAccountNo() {
		pressKeyToElement(driver, DeleteAccountUI.AccountNumber_input, Keys.TAB);	
	}

}
