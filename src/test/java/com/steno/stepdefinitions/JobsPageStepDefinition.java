package com.steno.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.util.List;
import com.steno.hooks.ApplicationHooks;
import com.steno.pages.Jobs;
import com.steno.pages.Login;
import com.steno.utilities.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobsPageStepDefinition {
	Login login = new Login(DriverFactory.getDriver());
	private Jobs jobs;

	@And("I navigate to the JOBS page")
	public void i_navigate_to_the_jobs_page() {
		jobs = login.openTheJobsTab();
		ApplicationHooks.scenario.log(" - Verify Job page openes as expected");
		assertTrue(jobs.getJobPageHeader().equals("What the current jobs for my state?"),
				"Verify the Jobs page opens as expected");

	}

	@When("I click on the {string} button")
	public void i_click_on_the_button(String jobsType) {
		if (jobsType.equals("GET UPCOMING JOBS")) {
			jobs.clickOnGetUpcomingJobs();
		} else if (jobsType.equals("IM FEELING LUCKY")) {
			jobs.clickOnIMFeelingLucky();
		}

	}

	@Then("I validate jobs in the table")
	public void i_validate_jobs_in_the_table() {
		ApplicationHooks.scenario.log(" - Verify Job details data matches with summary section");
		List<String> receivedList;
		receivedList = jobs.verifySummaryValuesToDetails();
		assertTrue(receivedList.isEmpty(), "Verify detailed data matches the summary row date, Firm and Deponent");

	}

}
