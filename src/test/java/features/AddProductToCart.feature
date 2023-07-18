Feature: Add product to cart
  This feature deals with adding the products to the shopping cart page

  Scenario: Check user not logged in can add a product to the shopping cart
    Given I am on the home page
    When Scroll down, hover over the Hero Hoodie
    And Select the product size
    And Select the product color
    And Click the add to cart button
    Then I should see the message confirming the producted added to cart
    And Click the shopping cart link
    And I should see the Hero Hoodie added to the shopping cart page