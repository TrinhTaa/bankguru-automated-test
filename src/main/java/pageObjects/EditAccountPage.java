package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.EditAccountUI;

public class EditAccountPage extends BasePage {
	WebDriver driver;
	
	EditAccountPage(WebDriver driver){
		this.driver = driver;
	}

	public boolean verifyElementVisible() {
		boolean status =  isElementDisplayed(driver, EditAccountUI.Heading_text);
		return status;
	}

	public void inputTextToAccountNumber(String text) {
		waitForElementVisible(driver, EditAccountUI.AccountNumber_input);
		sendkeyToElement(driver, EditAccountUI.AccountNumber_input, text);
	}

	public void sendTabToAccountNumber() {
		pressKeyToElement(driver, EditAccountUI.AccountNumber_input, Keys.TAB);
		
	}

	public String getWarningMessage() {
		waitForElementVisible(driver, EditAccountUI.Message_text);
		String message = getElementText(driver, EditAccountUI.Message_text);
		return message;
	}

	public void clickSubmitButton() {
		waitForElementClickable(driver, EditAccountUI.Submit_button);
		clickToElement(driver, EditAccountUI.Submit_button);		
	}

}
