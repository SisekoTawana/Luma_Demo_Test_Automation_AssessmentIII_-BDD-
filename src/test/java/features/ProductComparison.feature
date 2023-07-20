Feature: ProductComparison Feature
  This feature deals with adding and removing the products in the product comparison page

  Scenario: Verify if a user not logged in can add products to compare-MPV
    Given I am on the home page
    When  Scroll down, hover over the Radiant Tee and Click the add to compare link
    And I should see a success message for Radiant Tee
    And Click the comparison list link
    Then I should see the word Compare Products

  Scenario: Verify if a user not logged in can add products to compare-PDV
    Given I am on the home page
    When Scroll down, Click on Breath-Easy Tank
    And Add to compare the Breath-Easy Tank
    And I should see a success message for Breathe-Easy Tank
    And Click the comparison list link
    Then I should see the word Compare Products

  Scenario: Verify if a product can be removed from Compare Product list
    Given I am on the home page
    When Scroll down, Click on Argus All-Weather Tank
    And Add to compare the Argus All-Weather Tank
    And I should see a success message for Argus All-Weather Tank
    And Click the comparison list link
    Then I should see the word Compare Products
    And Click the remove icon and I should see the reassurance question pop-up
    And Click the OK button and I should see the confirmation of the removal