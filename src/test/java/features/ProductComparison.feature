Feature: ProductComparison Feature
  This feature deals with adding and removing the products in the product comparison page

  Scenario: Verify if a user not logged in can add products to compare-MPV
    Given I am on the home page
    When  Scroll down, hover over the Radiant Tee and Click the add to compare link
#    And I should see a success message
    And Click the comparison list link
    Then I should see the Radiant Tee to the product compare page

  Scenario: Verify if a user not logged in can add products to compare-PDV
    Given I am on the home page
    When Scroll down, Click on Breath-Easy Tank
    And Click the add to compare link
    And I should see a success message
    And Click the comparison list link
    Then I should see the Breath-Easy Tank to the product compare page

  Scenario: Verify if a product can be removed from Compare Product list
    Given I am on the home page
    When Scroll down, Click on Argus All-Weather Tank
    And Click the add to compare link
    And I should see a success message
    And Click the comparison list link
    Then I should see the Argus All-Weather Tank to the product compare page
    And Click the remove icon and I should see the reassurance question pop-up
    And Click the OK button
    And I should see the confirmation of the removal

  Scenario: