@tag
Feature: Purchase the order from ecommerce website

  Background:
    Given I landed on Ecommerce Page
    

  @Regression
  Scenario Outline: Positive Test of purchasing te order
    Given Loggedin with username <username> and <password>
    When I add product <productName> to cart
    And checkout the <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation screen

    Examples:
      | username               | password  | productName |
      | rajathpai078@gmail.com | Test@1234 | ZARA COAT 3 |
