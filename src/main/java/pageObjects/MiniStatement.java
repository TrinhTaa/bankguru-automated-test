package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.MiniSatementUI;

public class MiniStatement extends BasePage {
	WebDriver driver;
	
	MiniStatement(WebDriver driver){
		this.driver = driver;
	}

	public void inputTextToAccountNumber(String text) {
		waitForElementVisible(driver, MiniSatementUI.AccountNumber_input);
		sendkeyToElement(driver, MiniSatementUI.AccountNumber_input, text);
		
	}
	
	public void sendTabToAccountNumber() {
		pressKeyToElement(driver, MiniSatementUI.AccountNumber_input, Keys.TAB);
	}
	
	public String getWarningContent() {
		waitForElementVisible(driver, MiniSatementUI.Warning_text);
		String message = getElementText(driver, MiniSatementUI.Warning_text);
		return message;
	}

}
