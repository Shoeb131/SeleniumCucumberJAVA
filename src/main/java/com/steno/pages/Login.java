package com.steno.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.steno.utilities.ElementUtil;
import com.steno.utilities.GenericUtil;

public class Login {

	@FindBy(css = "input[class*='MuiInputBase'][type='text']")
	private WebElement userNameTextBox;
	@FindBy(css = "input[class*='MuiInputBase'][type='password']")
	private WebElement passwordTextBox;
	@FindBy(css = "button[class*='MuiButton-outlined'][type='button']")
	private WebElement logInButton;
	@FindBy(css = "div[data-qa] > h3")
	private WebElement logInConfirmationText;
	@FindBy(id = "failed")
	private WebElement loginFailedConfirmationText;
	@FindBy(css = "span[class^='MuiButton-label']")
	private WebElement logOutButton;
	@FindBy(xpath = "//*[text()='Jobs']")
	private WebElement jobTab;
	@FindBy(xpath = "//*[text()='Providers']")
	private WebElement providerTab;
	@FindBy(css = "span[class='MuiTab-wrapper']")
	private WebElement loginTab;

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean usernameFieldDisplayed() {
		log.info("Verifying if the user name field displayed");
		boolean flag = false;
		try {
			flag = GenericUtil.gu.waitForVisibilityOfElement(driver, userNameTextBox, 5).isDisplayed();
			return flag;
		} catch (Exception e) {
			return flag;
		}

	}

	public boolean passwordFieldDisplayed() {
		log.info("Verifying if the password field is displayed");
		boolean flag = false;
		try {
			flag = GenericUtil.gu.waitForVisibilityOfElement(driver, passwordTextBox, 5).isDisplayed();
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}

	public boolean logInButtonIsDisplayed() {
		log.info("Verifying if the Login button is displayed");
		boolean flag = false;
		try {
			flag = GenericUtil.gu.waitForVisibilityOfElement(driver, logInButton, 5).isDisplayed();
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}

	public String getPageTitle() {
		log.info("Getting the Login page title");
		return GenericUtil.gu.waitForVisibilityOfElement(driver, loginTab, 5).getText();
	}

	public void loginWithValidCredentials() {
		log.info("Login with valid credentials "+ElementUtil.eu.getData("username") );
		log.info("Login with valid credentials "+ElementUtil.eu.getData("password") );
		enterUserName(ElementUtil.eu.getData("username"));
		enterPassword(ElementUtil.eu.getData("password"));
		clickOnLoginButton();
		
	}
	public void enterUserName(String userName) {
		log.info("Entering the user name");
		GenericUtil.gu.waitForVisibilityOfElement(driver, userNameTextBox, 5).sendKeys(userName);
	}

	public void enterPassword(String password) {
		log.info("Entering the password");
		GenericUtil.gu.waitForVisibilityOfElement(driver, passwordTextBox, 5).sendKeys(password);
	}

	public void clickOnLoginButton() {
		log.info("Clicking on the Login Button");
		GenericUtil.gu.clickOnAWebElement(driver, logInButton, 5);
	}

	public void clickOnTheLogOutButton() {
		log.info("Clicking the Logout button");
		GenericUtil.gu.clickOnAWebElement(driver, logInButton, 5);
	}

	public String getLogInConfirmationText() {
		log.info("Getting login confirmation text");
		return GenericUtil.gu.waitForVisibilityOfElement(driver, logInConfirmationText, 5).getText();

	}

	public String getLogInFailedText() {
		log.info("Getting login failed Text");
		return GenericUtil.gu.waitForVisibilityOfElement(driver, loginFailedConfirmationText, 1).getText();
	}

	public Jobs openTheJobsTab() {
		log.info("Opening the Jobs Tab");
		GenericUtil.gu.clickOnAWebElement(driver, jobTab, 5);
		return new Jobs(driver);
	}

	public Providers openTheProvidersTab() {
		log.info("Opening the providers tab");
		GenericUtil.gu.clickOnAWebElement(driver, providerTab, 5);
		return new Providers(driver);
	}
}
