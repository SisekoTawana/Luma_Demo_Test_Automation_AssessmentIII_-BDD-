Feature: ProductComparison Feature
  This feature deals with all the products in the webpage

  Scenario: Verify if a user not logged in can add products to compare
    Given I am on the home page
    When  Scroll down, hover over the Radiant Tee and Click the add to compare link
    Then I should see a success message
    And Click the comparison list link
    Then I should see the Radient Tee to the product compare page