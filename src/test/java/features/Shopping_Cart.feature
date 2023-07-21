Feature: Shopping Cart Functionality

  Scenario: Verify if a user not logged in can add products to compare-MPV
    Given I am on the home page
    When Scroll down, hover over the Radiant Tee and Click the add to compare link
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

  Scenario: Check user not logged in can add a product to the shopping cart
    Given I am on the home page
    When Scroll down, hover over the Hero Hoodie, Select the product size,Select the product color
    And Add to cart the Hero Hoodie
    Then I should see the message confirming that Hero Hoodie is added
    And Click the shopping cart link and I should see the word Shipping Cart

  Scenario: Use valid credentials to log in the website and Verify if a user logged in can add product to compare multiple products view without selecting its size and colour
    Given I am on the home page
    When Navigate and click sign in
    And I should see Customer Login
    And Enter Email, Password and Click Sign In
    Then I should see the word Welcome
 # Scenario: Verify if a user logged in can add product to compare multiple products view without selecting its size and colour
    Given I am on the home page
    When Scroll down, hover over the Men tab and Tops tab
    And Click the Tees tab and I should see the word Tees
    And Hover over the Logan HeatTec Tee
    And Click the add to cart button
    Then I should see the error message

  Scenario: Check user logged in can add product to cart using the search field
    Given I am on the home page
    When Navigate and click sign in
    And I should see Customer Login
    And Enter Email, Password and Click Sign In
    Then I should see the word Welcome
    Given I am on the home page
    When Navigate the search field and enter Miko Pullover Hoodie
    And Click the search icon and I should see the message for the search
    Then Click the Miko Pullover Hoodie, Select the product size, Select the product color
    And Add to cart the Miko Pullover Hoodie
    And I should see the message confirming that Miko Pullover Hoodie is added
    And Click the shopping cart link and I should see the word Shipping Cart
  #Scenario: Checking the user out
    Given I am on the home page
    When Navigate to the cart icon and click it
    And Navigate to the proceed to checkout button and click it
    And I should see the word Order Summary
    Then Enter First name, Last name, Company, Street address, City, Province, Postal Code, Country, Phone number and Click the next button
    And I should see Ship To: and click the place order button
    And I should see Thank you for your purchase!