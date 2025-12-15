@tag
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Login Page
    Given I landed on Ecommerce Page
    When Loggedin with username <username> and <password> 
    Then "Incorrect email or password." message is displayed

    Examples:
      | username               | password  |
      | rajathpai078@gmail.com | Test@1233 |
