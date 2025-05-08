package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.DashBoardUI;

public class DashBoardPage extends BasePage {
	WebDriver driver;
	
	DashBoardPage(WebDriver driver){
		this.driver = driver;
	}

	public void clickToMenuByText(String text) {
		scrollToElementOnDown(driver, DashBoardUI.Dynamic_menu, text);
		waitForElementClickable(driver, DashBoardUI.Dynamic_menu, text);
		clickToElement(driver, DashBoardUI.Dynamic_menu, text);
	}

    public void InputDataByName(String value) {
       waitForElementVisible(driver, DashBoardUI.ID_input);
	   sendkeyToElement(driver, DashBoardUI.ID_input, value);
    }

	public void ClickToSubmitAccount(WebDriver driver) {
		clickToElement(driver, DashBoardUI.Submit_button);
	}

	public void clickToTextboxByFieldName() {
        waitForElementClickable(driver, DashBoardUI.ID_input);
        clickToElement(driver, DashBoardUI.ID_input);
    }

    public void sendTabToElementByName() {
        pressKeyToElement(driver, DashBoardUI.ID_input, Keys.TAB);
    }

	public String getMessageOfFieldByName() {
		waitForElementVisible(driver, DashBoardUI.ID_message);
		String message = getElementText(driver, DashBoardUI.ID_message);
		return message;
	}

}
