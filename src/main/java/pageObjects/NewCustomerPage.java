package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.NewCustomerUI;

public class NewCustomerPage extends BasePage {
	WebDriver driver;
	
	NewCustomerPage(WebDriver driver){
		this.driver = driver;
	}

	public void clickToTextboxByName(String name) {
		scrollToElementOnDown(driver, NewCustomerUI.Dynamic_input, name);
		waitForElementClickable(driver, NewCustomerUI.Dynamic_input, name);
		clickToElement(driver, NewCustomerUI.Dynamic_input, name);
	}
	
	public String getMessageOfFieldByName(String name) {
		waitForElementVisible(driver, NewCustomerUI.Dynamic_Message_label, name);
		String actual_message = getElementText(driver, NewCustomerUI.Dynamic_Message_label, name);
		return actual_message;
	}

	public void sendTabToFieldByName(String name) {
		pressKeyToElement(driver, NewCustomerUI.Dynamic_input, Keys.TAB, name);
	}

	public void inputDataToTextboxByName(String name, String value) {
		scrollToElementOnDown(driver, NewCustomerUI.Dynamic_input, name);
		pressKeyToElement(driver, NewCustomerUI.Dynamic_input, Keys.CLEAR, name);
		sendkeyToElement(driver, NewCustomerUI.Dynamic_input, value, name);		
	}

	public String getValueOfTextboxByName(String name, String attribute) {
		String value = getElementAttribute(driver, NewCustomerUI.Dynamic_input, attribute, name);
		return value;
	}
	
	public void verifyFieldNameVisible(String textvalue) {
		scrollToElementOnDown(driver, NewCustomerUI.Dynamic_fieldName, textvalue);
		waitForElementVisible(driver, NewCustomerUI.Dynamic_fieldName, textvalue);
	}
	
	 public void clearInputField(String name) {
		 waitForElementVisible(driver, NewCustomerUI.Dynamic_input, name);
		 pressKeyToElement(driver, NewCustomerUI.Dynamic_input, Keys.CLEAR , name);
	 }
	
	

}
