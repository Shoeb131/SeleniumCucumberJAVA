package com.steno.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/steno/features", glue = {
		"com.steno.stepdefinitions", "com/steno/hooks" },
		tags="@Functional or ~@JobPage",
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }

)

public class TestRunner extends AbstractTestNGCucumberTests {

}
