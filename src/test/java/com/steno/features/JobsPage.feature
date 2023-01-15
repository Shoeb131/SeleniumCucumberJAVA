@JobPage
Feature: Verify the JOBS page functionality

  @Functional
  Scenario Outline: Verify Get upcoming jobs
    Given I navigate to the system
    And I login with valid credentials
    And I navigate to the JOBS page
    When I click on the "GET UPCOMING JOBS" button
    Then I validate jobs in the table

  @Functional
  Scenario Outline: Verify I am feeling Lucky jobs
    Given I navigate to the system
    And I login with valid credentials
    And I navigate to the JOBS page
    When I click on the "IM FEELING LUCKY" button
    Then I validate jobs in the table
