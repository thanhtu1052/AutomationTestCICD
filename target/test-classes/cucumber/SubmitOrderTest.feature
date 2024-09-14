
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given Logged with username <email> and password <password>
    When I add product <productName1>, <productName2> to Cart
    And Check out <productName1>, <productName2> in Cart and submit order
    Then "THANKYOU FOR THE ORDER" message is displayed on Cofirmation Page

    Examples: 
      | email                 | password    | productName1 | productName2
      | thanhtu1052@gmail.com | Thanhtu1052 | ZARA COAT 3  | ADIDAS ORIGINAL
      
