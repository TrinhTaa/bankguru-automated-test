package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.DeleteCustomerUI;

public class DeleteCustomer extends BasePage {
    WebDriver driver;

    DeleteCustomer (WebDriver driver){
        this.driver = driver;
    }

    public void clickToTextboxByFieldName(String fieldName) {
        waitForElementClickable(driver, DeleteCustomerUI.Dynamic_input, fieldName);
        clickToElement(driver, DeleteCustomerUI.Dynamic_input, fieldName);
    }

    public void sendTabToFieldByname(String fieldName) {
        pressKeyToElement(driver, DeleteCustomerUI.Dynamic_input, Keys.TAB, fieldName);
    }

    public String getMessageOfFieldByName(String fieldName) {
        waitForElementVisible(driver, DeleteCustomerUI.Dynamic_message, fieldName);
        String message = getElementText(driver, DeleteCustomerUI.Dynamic_message, fieldName);
        return message;
    }

	public void SendDataToElementByName(String FieldName, String value) {
		waitForElementVisible(driver, DeleteCustomerUI.Dynamic_input, FieldName);
		sendkeyToElement(driver, DeleteCustomerUI.Dynamic_input, value, FieldName);
		
	}

}
