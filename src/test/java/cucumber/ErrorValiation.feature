@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
Background:


  @ErrorValidation
  Scenario Outline: Negative test
    Given I landed on Ecommerce Page
    When Logged with username <email> and password <password>
    Then "Incorrect email or password" validation message is displayed

    Examples: 
      | email                 | password    | productName1 | productName2
      | thanhtu1052@gmail.com | Thanhtu1052 | ZARA COAT 3  | ADIDAS ORIGINAL
      
//landingPage.loginApplication("thanhtu10522@gmail.com", "Thanhtu1052");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMess());