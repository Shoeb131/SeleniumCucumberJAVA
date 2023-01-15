package com.steno.stepdefinitions;

import static org.testng.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.steno.hooks.ApplicationHooks;
import com.steno.pages.Login;
import com.steno.utilities.DriverFactory;
import com.steno.utilities.ElementUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
	private Logger log = LogManager.getLogger(this.getClass().getName());
	Login login = new Login(DriverFactory.getDriver());
	
	@Given("I navigate to the system")
	public void navigate_to_the_system() {
		DriverFactory.getDriver().get(ElementUtil.eu.getData("url"));
		ApplicationHooks.scenario.log(" - The Application launched successfully.");
		log.info(" - The Application launched successfully.");
	}

	@Then("Verify Title of the login page is {string}")
	public void verify_title_of_the_login_page_is(String title) {
		ApplicationHooks.scenario.log(" - Verify page title shows as expected");
		assertTrue(login.getPageTitle().equals(title), "Verify the page title shows as expected");
	}

	@And("Verify Username field displayed")
	public void verify_username_field_displayed() {
		ApplicationHooks.scenario.log(" - Verify UserName field displayed");
		assertTrue(login.usernameFieldDisplayed(), "Verify UserName field displayed");
	}

	@And("Verify Password field displayed")
	public void verify_password_field_displayed() {
		ApplicationHooks.scenario.log(" - Verify password field displayed");
		assertTrue(login.passwordFieldDisplayed(), "Verify password field displayed");
	}

	@And("Verify Login button is displayed")
	public void verify_login_button_is_displayed() {
		ApplicationHooks.scenario.log(" - Verify Login button displayed");
		assertTrue(login.logInButtonIsDisplayed(), "Verify Login button displayed");

	}
	@And("I login with valid credentials")
	public void login_with_valid_credentials() {
		login.loginWithValidCredentials();
	}

	@And("I enter {string} and {string}")
	public void enter_username_password(String userName, String password) {
		login.enterUserName(userName);
		login.enterPassword(password);

	}

	@When("I click on the login button")
	public void click_on_the_login_button() {
		login.clickOnLoginButton();
	}

	@Then("I validate the outcomes for {string} and {string}")
	public void validate_the_outcome(String userName, String password) {

		if (ElementUtil.eu.getData("username").equals(userName)
				&& ElementUtil.eu.getData("password").equals(password)) {
			assertTrue(login.getLogInConfirmationText().equals("LOGGED IN"), "Verify login to the system successfully");
		} else {
			assertTrue(login.getLogInFailedText().equals("LOGIN FAILED"),
					"Verify the system shows login failed text as expected");
		}

	}

	@And("I logout from the system")
	public void logout_from_the_system() {
		login.clickOnTheLogOutButton();
	}

	@Then("Verify the page url is {string}")
	public void verify_the_page_url_is(String expectedUrl) {
		String actualUrl = DriverFactory.getDriver().getCurrentUrl();
		assertTrue(expectedUrl.equals(actualUrl), "Verify the login url shows as expected");
	}

}
