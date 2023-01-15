package com.steno.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.steno.utilities.GenericUtil;

public class Jobs {

	@FindBy(xpath = "//*[text()='Get Upcoming Jobs']")
	private WebElement getUpComingJobsButton;
	@FindBy(xpath = "//span[contains(text(),'m Feeling Lucky')]")
	private WebElement iAmFeelingLuckyButton;
	@FindBy(css = "div[class='Jobs']>h6")
	private WebElement jobsPageHeader;
	@FindBy(css = "tr[data-qa*=summary]")
	private List<WebElement> tableTotalRowsList;
	@FindBy(css = "button[aria-label*='expand']")
	private List<WebElement> expandButton;
	@FindBy(css = "th[role='cell'][scope='row']")
	private List<WebElement> summaryDateList;
	@FindBy(css = "td[class*='MuiTableCell-alignRight']")
	private List<WebElement> summaryNameList;
	@FindBy(xpath = "(//p[contains(@class,'body1')])")
	private List<WebElement> rowDetails_2;
	@FindBy(css = "h6[class*='MuiTypography-h6']")
	private List<WebElement> jobDetailsList;
	@FindBy(css = "p[class*='MuiTypography-body1']")
	private List<WebElement> jobDetaislBodyList;

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private WebDriver driver;

	public Jobs(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnGetUpcomingJobs() {
		log.info("Clicking on the Get up coming Jobs");
		GenericUtil.gu.clickOnAWebElement(driver, getUpComingJobsButton, 1);
	}

	public void clickOnIMFeelingLucky() {
		log.info("Clicking on the I am feeling Lucky");
		GenericUtil.gu.clickOnAWebElement(driver, iAmFeelingLuckyButton, 1);
	}

	public String getJobPageHeader() {
		log.info("Getting the Job page header");
		return GenericUtil.gu.waitForVisibilityOfElement(driver, jobsPageHeader, 1).getText();
	}

	public List<String> verifySummaryValuesToDetails() {
		log.info("Verifying the Summary to Details values");
		int totalTableRows = tableTotalRowsList.size();
		List<String> missMatchDetailsArrayList = new ArrayList<>();
		int incrementNum = 0;
		for (int i = 0; i < totalTableRows; i++) {

			GenericUtil.gu.clickOnAWebElement(driver, expandButton.get(i), 2);

			String summaryDate = summaryDateList.get(i).getText();
			String summaryFirmName = summaryNameList.get(incrementNum).getText();
			String summaryDeponentName = summaryNameList.get(incrementNum + 1).getText();

			WebElement rowDetails = driver
					.findElement(By.xpath("(//p[contains(@class,'MuiTypography-body1')])[" + (i + 1) + "]"));
			GenericUtil.gu.waitForElementToBeAvailable(driver, rowDetails, 30);

			String jobRowDetails_1 = jobDetailsList.get(incrementNum + 1).getText();
			String jobRowDetails_2 = jobDetailsList.get(incrementNum + 2).getText();

			if (!jobRowDetails_2.contains(summaryFirmName) || !jobRowDetails_2.contains(summaryDeponentName)
					|| !jobRowDetails_1.contains(summaryDate)) {
				missMatchDetailsArrayList.add(jobRowDetails_2);
				log.warn("Summary and Deatils value miss match to " + missMatchDetailsArrayList);
			}
			incrementNum = incrementNum + 2;
		}
		return missMatchDetailsArrayList;
	}

}
