package com.steno.stepdefinitions;

import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import com.steno.pages.Login;
import com.steno.pages.Providers;
import com.steno.utilities.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProvidersPageStepDefinition {

	Login login = new Login(DriverFactory.getDriver());
	private Providers providers;

	@And("Navigate to the PROVIDERS page")
	public void navigate_to_the_provider_page() {
		providers = login.openTheProvidersTab();
		assertTrue(providers.getProviderPageHeader().equals("Providers"), "Verify Provider page opens as expected");
	}

	@And("Click on the Add Provider button")
	public void click_on_the_add_provider() {
		providers.clickOnAddProviderButton();
	}

	@When("I Fill in the {string}, {string}, and {string} fields with valid information")
	public void fill_in_the_and_fields_with_valid_information(String name, String email, String rating) {
	
		providers.setNewProvidersInformations(name, email, rating);
	}

	@And("Click on the Add button to add the new provider")
	public void click_on_the_add_button_to_add_the_new_provider() {
		providers.clickAddProviderPopUpAddButton();

	}

	@Then("Validate if the provider {string}, {string}, and {string} is successfully added to the system.")
	public void validate_if_the_provider_is_successfully_added_to_the_system(String providerName, String emailAddress, String rating) {
		Map<String, String> tableValuesInMap = new HashMap<>();
		tableValuesInMap = providers.getTheProviderFromThetableByEmailAddress(emailAddress);

		assertTrue(tableValuesInMap.get("Name").equals(providerName),
				"Verify the provider providerName added as expected");
		assertTrue(tableValuesInMap.get("Email").equals(emailAddress), "Verify the email address added as expected");
		assertTrue(tableValuesInMap.get("Rating").equals(rating), "Verify the rating added as expected");

	}

	@And("I Delete the added Provider {string} from the table")
	public void delete_the_added_provider_from_the_table(String emailAddress) throws InterruptedException {
		providers.deleteTherow(emailAddress);
	}

	@Then("Validate if the {string}, {string}, and {string} deleted from the table")
	public void validate_the_provider_deleted_from_the_table(String name, String email, String rating) {
		Integer providerRow = providers.findProviderRowNumberByEmailFromTheTable(email);
		assertTrue(providerRow == 0, "Verify the provider deleted from the table");
	}

}
