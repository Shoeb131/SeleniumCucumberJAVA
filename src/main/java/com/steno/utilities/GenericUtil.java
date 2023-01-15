package com.steno.utilities;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtil {
	public static GenericUtil gu = new GenericUtil();

		
	/**
	 * @param driver  driver the WebDriver instance
	 * @param element the WebElement
	 * @param time the time unit in second
	 */
	public void clickOnAWebElement(WebDriver driver, WebElement element, int time) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(time));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}


	/**
	 * @param driver the WebDriver instance
	 * @param element the WebElement
	 * @param time duration of second to wait
	 * @return the click able element
	 */
	public WebElement waitForElementToBeAvailable(WebDriver driver, WebElement element, int time) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(time));
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	

	/**
	 * @param driver the WebDriver instance
	 * @param ele element the WebElement
	 * @param time time duration of second to wait
	 * @return the visible element
	 */
	public WebElement waitForVisibilityOfElement(WebDriver driver, WebElement ele, int time) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
		wt.until(ExpectedConditions.visibilityOf(ele));
		return ele;
	}
	
	

}

