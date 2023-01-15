package com.steno.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.steno.utilities.GenericUtil;

public class Providers {

	@FindBy(xpath = "//*[text()='Providers' and contains(@class,'MuiTypography-h6')]")
	private WebElement providerPageHeader;
	@FindBy(xpath = "//*[text()='Add Provider']")
	private WebElement addNewProviderButton;
	@FindBy(css = "input[class*='MuiOutlinedInput-input'][type='text']")
	private WebElement addProviderNameTextBox;
	@FindBy(css = "input[class*='MuiFilledInput-input'][type='text']")
	private WebElement addProviderEmailTextBox;
	@FindBy(css = "button[data-qa='modal-button-cancel']")
	private WebElement addProviderCancelButton;
	@FindBy(id = "pthenqimpliesnotqthennotp")
	private WebElement addProviderPopupAddButton;
	@FindBy(css = "div[data-qa='select-rating']")
	private WebElement addProviderProviderRatingDropdownButton;
	@FindBy(xpath = "//div[contains(text(),'NEW')]")
	private WebElement providerRatingDropdown;
	@FindBy(css = "button[class*='MuiIconButton-root']")
	private List<WebElement> deleteButtonList;
	@FindBy(css = "tr[data-qa*='row']")
	private List<WebElement> tableRowsList;
	@FindBy(css = "tr[class*='MuiTableRow-head']>th")
	private List<WebElement> tableColumnsList;

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private WebDriver driver;

	public Providers(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setNewProvidersInformations(String name, String email, String providerRating) {
		log.info("Seting the Provider with " + name + "email " + email + " the rating " + providerRating);
		GenericUtil.gu.waitForVisibilityOfElement(driver, addProviderNameTextBox, 1).sendKeys(name);
		GenericUtil.gu.waitForVisibilityOfElement(driver, addProviderEmailTextBox, 1).sendKeys(email);
		selectProviderRating(providerRating);

	}

	public void clickOnAddProviderButton() {
		log.info("Clicking on the Add Provider Button");
		GenericUtil.gu.clickOnAWebElement(driver, addNewProviderButton, 1);
	}

	public void setProviderName(String providerName) {
		log.info("Seting the provider name");
		GenericUtil.gu.waitForVisibilityOfElement(driver, addProviderNameTextBox, 1).sendKeys(providerName);
	}

	public void setProviderEmail(String emailAddress) {
		log.info("Seting the provider email address");
		GenericUtil.gu.waitForVisibilityOfElement(driver, addProviderEmailTextBox, 1).sendKeys(emailAddress);
	}

	public void selectProviderRating(String providerRating) {
		log.info("Seting the provider providerRating");
		GenericUtil.gu.clickOnAWebElement(driver, providerRatingDropdown, 1);
		driver.findElement(By.cssSelector("li[data-value='" + providerRating.toUpperCase() + "']")).click();
	}

	public void clickAddProviderPopUpAddButton() {
		log.info("Clicking on the Add provider popup Add Button");
		GenericUtil.gu.clickOnAWebElement(driver, addProviderPopupAddButton, 1);
	}

	public String getProviderPageHeader() {
		log.info("Getting provider page header");
		return GenericUtil.gu.waitForVisibilityOfElement(driver, providerPageHeader, 1).getText();
	}

	public void deleteTherow(String emailAddress) throws InterruptedException {
		log.info("Deleting provider row for email " + emailAddress);
		int providerRow = findProviderRowNumberByEmailFromTheTable(emailAddress);
		GenericUtil.gu.clickOnAWebElement(driver, deleteButtonList.get(providerRow - 1), 1);
		Thread.sleep(1000);
	}

	public int findProviderRowNumberByEmailFromTheTable(String emailAddress) {
		log.info("Finding the Provider row number in the table by email " + emailAddress);

		int emailAddressColumnNumber = getColumnNumberByHeader("Email");
		int totalTableRows = tableRowsList.size();
		int providerRowNumber = 0;
		for (int i = 1; i < totalTableRows + 1; i++) {

			String tableEmailName = driver
					.findElement(By
							.xpath("(//tr[contains(@data-qa,'row')])[" + i + "]/td[" + emailAddressColumnNumber + "]"))
					.getText();
			if (tableEmailName.equals(emailAddress)) {
				providerRowNumber = i;
				break;
			}
		}
		return providerRowNumber;
	}

	private int getColumnNumberByHeader(String headerName) {
		int i;
		for (i = 0; i < tableColumnsList.size(); i++) {
			String header = tableColumnsList.get(i).getText();
			if (header.equals(headerName)) {
				break;
			}
		}
		return i + 1;
	}

	public Map<String, String> getTheProviderFromThetableByEmailAddress(String emailAddress) {
		log.info("Getting the Provider from the table by email " + emailAddress);
		HashMap<String, String> tableColumnMap = new HashMap<String, String>();

		int totalTableColumns = tableColumnsList.size();
		int providerRowNumber = findProviderRowNumberByEmailFromTheTable(emailAddress);
		for (int j = 1; j < totalTableColumns + 1; j++) {

			String tableHeaderName = tableColumnsList.get(j - 1).getText();
			String tableCellValue = driver
					.findElement(By.xpath("(//tr[contains(@data-qa,'row')])[" + providerRowNumber + "]/td[" + j + "]"))
					.getText();

			tableColumnMap.put(tableHeaderName, tableCellValue);
		}

		return tableColumnMap;

	}

}
