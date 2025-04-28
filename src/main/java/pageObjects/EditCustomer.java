package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.EditCustomerUI;

public class EditCustomer extends BasePage{
    WebDriver driver;

    EditCustomer (WebDriver driver){
        this.driver = driver;
    }

    public void clickToAddressElement(WebDriver driver){
        waitForElementClickable(driver, EditCustomerUI.Address_textarea);
        clickToElement(driver, EditCustomerUI.Address_textarea);
    }

    public void sendTabToAddress(WebDriver driver){
        sendKeyboardToElement(driver, EditCustomerUI.Address_textarea, Keys.TAB);
    }

    public void clickToElementByName(String name) {
        scrollToElementOnDown(driver, EditCustomerUI.Dynamic_input, name);;
        waitForElementClickable(driver, EditCustomerUI.Dynamic_input, name);
        clickToElement(driver, EditCustomerUI.Dynamic_input, name);
    }

    public void sendTabToElementByName(String name) {
        pressKeyToElement(driver, EditCustomerUI.Dynamic_input, Keys.TAB, name);
    }

    public String getMessageOfFieldByName(String fieldName) {
        waitForElementVisible(driver, EditCustomerUI.Dynamic_message, fieldName);
        String message = getElementText(driver, EditCustomerUI.Dynamic_message, fieldName);
        return message;
    }

    public void SendTextToElementByName(String name, String value) {
       scrollToElementOnDown(driver, EditCustomerUI.Dynamic_input, name);
       waitForElementVisible(driver, EditCustomerUI.Dynamic_input, name);
       clickToElement(driver, EditCustomerUI.Dynamic_input, name);
       sendkeyToElement(driver, EditCustomerUI.Dynamic_input, value, name);
    }

    public void ClickToSubmitButton(WebDriver driver) {
        waitForElementClickable(driver, EditCustomerUI.Submit_button);
        clickToElement(driver, EditCustomerUI.Submit_button);
    }

    public void EditCustomerPageShouleBeVisible(WebDriver driver) {
        waitForElementVisible(driver, EditCustomerUI.Heading_text);
    }
    public void sendTextToAddress(String value) {
        waitForElementClickable(driver, EditCustomerUI.Address_textarea);
        sendkeyToElement(driver, EditCustomerUI.Address_textarea, value);
    }

    public String getWarningMessageOfAddress() {
        waitForElementVisible(driver, EditCustomerUI.Address_message);
        String message = getElementText(driver, EditCustomerUI.Address_message);
        return  message;
    }

	public String getValueOfTextboxByName(String name, String attribute) {
		scrollToElementOnDown(driver, EditCustomerUI.Dynamic_input, name);
		waitForElementVisible(driver, EditCustomerUI.Dynamic_input, name);
		String value = getElementAttribute( driver, EditCustomerUI.Dynamic_input, attribute, name);
		return value;
	}

}
