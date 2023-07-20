Feature: Logging in
  This feature deals with logging in

  Scenario: Use valid credentials to log in the website
    Given I am on the home page
    When Navigate and click sign in
    And I should see Customer Login
