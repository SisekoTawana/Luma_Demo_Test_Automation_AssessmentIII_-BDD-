Feature: Checking out
  This feature deals with the checking out of the user

  Scenario: Checking the user out
    Given I am on the home page
    When Navigate to the cart icon and click it
    And Navigate to the proceed to checkout button and click it
    And I should see the word "Order Summary"
    Then Enter First name, Last name, Company, Street address, City, Province, Postal Code, Country, Phone number and Click the next button
    And I should see "Ship To:" and click the place order button
    And I should see "Thank you for your purchase!"