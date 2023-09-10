@Web @Login
Feature: Verify Login feature

  @Success
  Scenario: Verify login functionality with valid credentials
    Given user navigates to login screen
    When user tries to login with valid credentials
    Then verify text from homescreen
