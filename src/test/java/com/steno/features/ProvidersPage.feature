@ProviderPage
Feature: Verify Provider page functionality

  @Functional
  Scenario Outline: Verifying adding a new provider
    Given I navigate to the system
    And I login with valid credentials
    And Navigate to the PROVIDERS page
    And Click on the Add Provider button
    When I Fill in the "<name>", "<email>", and "<rating>" fields with valid information
    And Click on the Add button to add the new provider
    Then Validate if the provider "<name>", "<email>", and "<rating>" is successfully added to the system.

    Examples: 
      | name   | email            | rating |
      | Shoeb  | shoeb@gmail.com  | GREAT  |
      | Shoeb1 | shoeb1@gmail.com | GOOD   |
      | Shoeb4 | shoeb4@gmail.com | BAD    |

  @Functional
  Scenario Outline: Verify Deleting a provider from the provider table
    Given I navigate to the system
    And I login with valid credentials
    And Navigate to the PROVIDERS page
    And Click on the Add Provider button
    When I Fill in the "<name>", "<email>", and "<rating>" fields with valid information
    And Click on the Add button to add the new provider
    And I Delete the added Provider "<email>" from the table
    Then Validate if the "<name>", "<email>", and "<rating>" deleted from the table

    Examples: 
      | name   | email            | rating    |
      | Shoeb2 | shoeb2@gmail.com | New       |
      | Shoeb3 | shoeb3@gmail.com | Preferred |
