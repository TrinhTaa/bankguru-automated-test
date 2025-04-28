package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private long longTimeout = 30;
	private long shortTimeout = 5;
	
	static public BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void overrideGlobalTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public void openNewTabWithUrl(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.open(" + url + ", 'NEW')");
		SleepInSecond(2);
		openPageUrl(driver, url);
	}
	
	public void closeTheCurrentTab(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.close()");
		SleepInSecond(2);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public Set<String> getWindowHandles(WebDriver driver){
		return driver.getWindowHandles();
	}
	
	public void switchToWindowByID(WebDriver driver, String windowId) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowId)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void switchToNewWindow(WebDriver driver) {
		driver.switchTo().window("NEW");
	}
	
	public void closeAllTabWindowParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	private By getByLocator(String locator) {
		By by = null;
		if(locator.startsWith("id") || locator.startsWith("Id") || locator.startsWith("ID")) {
			by =By.id(locator.substring(3));
		}else if(locator.startsWith("name") || locator.startsWith("Name") || locator.startsWith("NAME")) {
			by =By.name(locator.substring(5));
		}else if(locator.startsWith("class") || locator.startsWith("Class") || locator.startsWith("CLASS")) {
			by =By.className(locator.substring(6));
		}else if(locator.startsWith("css") || locator.startsWith("Css") || locator.startsWith("CSS")) {
			by =By.cssSelector(locator.substring(4));
		}else if(locator.startsWith("xpath") || locator.startsWith("XPath") || locator.startsWith("XPATH")) {
			by =By.xpath(locator.substring(6));
		}else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}
	
	private String getDynamicXpath(String xpathLocator, String... values) {
		if(xpathLocator.startsWith("xpath") || xpathLocator.startsWith("XPath") || xpathLocator.startsWith("XPATH")) {
			xpathLocator = String.format(xpathLocator, (Object[]) values);
		}
		return xpathLocator;
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(getDynamicXpath(locator)));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String locator){
		return driver.findElements(getByLocator(locator));
	}
	
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookieAndReloadPage(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
		SleepInSecond(3);
		refreshCurrentPage(driver);
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
	}
	
	public void clickToElementIfVisible(WebDriver driver, String locator) {
		if(isElementDisplayed(driver, locator)) {
			clickToElement(driver, locator);
		}
	}
	
	public void clickToElementIfVisible(WebDriver driver, String locator, String... dynamicValues) {
		if(isElementDisplayed(driver, locator, dynamicValues)) {
			clickToElement(driver, locator, dynamicValues);
		}
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String textValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendKeyToDateTextbox(WebDriver driver, String locator, String textValue) {
		removeAttributeInDOM(driver, locator, "type");
		SleepInSecond(2);
		waitForElementVisible(driver, locator);
		sendkeyToElement(driver, locator, textValue);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locator, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public void selectCustomDropDown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		clickToElement(driver, parentLocator);
		waitForAllElementPresence(driver, childLocator);
		List<WebElement> speedDropdownItems = getWebElements(driver, childLocator);
		for (WebElement item : speedDropdownItems) {
			if(item.getText().trim().equals(expectedItem)) {
				item.click();
				break;
			}
		}
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		SleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		List<WebElement> speedDropdownItems = driver.findElements(getByLocator(childXpath));
		for (WebElement item : speedDropdownItems) {
			if(item.getText().trim().equals(expectedTextItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getText();
	}
	
	public String getElementCSSValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size();
	}
	
	public int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElements(driver, getDynamicXpath(locator, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(!isElementSelected(driver, locator)) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		if(!isElementSelected(driver, getDynamicXpath(locator, dynamicValues))) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(isElementSelected(driver, locator)) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		if(isElementSelected(driver, getDynamicXpath(locator, dynamicValues))) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, locator);
		overrideGlobalTimeOut(driver, longTimeout);
		if(elements.size() == 0) {
			return true;
		}else if(elements.size() != 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicValues) {
		overrideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, getDynamicXpath(locator, dynamicValues));
		overrideGlobalTimeOut(driver, longTimeout);
		if(elements.size() == 0) {
			return true;
		}else if(elements.size() != 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator,  String... dynamicValues) {
		new Actions(driver).moveToElement(getWebElement(driver, getDynamicXpath(locator, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, String name, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(driver, getDynamicXpath(locator, dynamicValues)), key).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator, String... dynamicValues) {
		new Actions(driver).doubleClick(getWebElement(driver, getDynamicXpath(locator, dynamicValues))).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDrop(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceXpath), getWebElement(driver, targetXpath)).perform();
	}
	
	public void sendKeyboardToElement(WebDriver driver, String locator, Keys tab) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), tab).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void hightlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void hightlightElement(WebDriver driver, String locator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}
	
	public void addAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		return status;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator, String...dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return status;
	}

	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		SleepInSecond(3);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeOut(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, locator)));
		overrideGlobalTimeOut(driver, longTimeout);
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	public void waitForElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForAllElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}
	public void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_PATH_FOLDER;
		String fullFilePaths = "";
		for (String fileName : fileNames) {
			fullFilePaths = fullFilePaths + filePath + fileName + "\n";
		}
		fullFilePaths.trim();
		getWebElement(driver, locator).sendKeys(fullFilePaths);
	}
	
}
