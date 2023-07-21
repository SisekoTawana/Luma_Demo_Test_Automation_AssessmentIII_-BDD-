#Feature: Add product to cart
 # This feature deals with adding the products to the shopping cart page
#
 # Scenario: Check user not logged in can add a product to the shopping cart
  #  Given I am on the home page
   # When Scroll down, hover over the Hero Hoodie, Select the product size,Select the product color
    #And Add to cart the Hero Hoodie
    #Then I should see the message confirming that Hero Hoodie is added
    #And Click the shopping cart link and I should see the word Shipping Cart

  #Scenario: Check user logged in can add product to cart using the search field
   # Given I am on the home page
    #When Navigate the search field and enter Miko Pullover Hoodie
    #And Click the search icon and I should see the message for the search
    #Then Click the Miko Pullover Hoodie, Select the product size, Select the product color
    #And Add to cart the Miko Pullover Hoodie
    #And I should see the message confirming that Miko Pullover Hoodie is added
    #And Click the shopping cart link and I should see the word Shipping Cart