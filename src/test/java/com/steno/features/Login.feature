@Login
Feature: Techsteno login feature

  @Functional
  Scenario Outline: Verify login with valid credentials
    Given I navigate to the system
    Then Verify Title of the login page is "LOGIN"
    And Verify Username field displayed
    And Verify Password field displayed
    And Verify Login button is displayed
    And I enter "<username>" and "<password>"
    When I click on the login button
    Then I validate the outcomes for "<username>" and "<password>"
    And I logout from the system
    Then Verify the page url is "https://preview-16.techsteno.com/takehome/automation"

    Examples: 
      | username | password         |
      | shoeb    | Y1g7a0BPfFlCET%g |

  @Functional
  Scenario Outline: Verify login with invalid credentials
    Given I navigate to the system
    And I enter "<username>" and "<password>"
    When I click on the login button
    Then I validate the outcomes for "<username>" and "<password>"

    Examples: 
      | username | password         |
      | shoeb1   | Y1g7a0BPfFlCET%g |
      | shoeb    | Y1g7a0BPfFlCET%  |
      | shoeb22  | Y1g7a0BPlCET%g   |
