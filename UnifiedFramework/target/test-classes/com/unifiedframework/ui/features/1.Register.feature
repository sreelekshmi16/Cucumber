@Web @Register
Feature: Verify Login feature

  @Success
  Scenario: Verify Register functionality with valid credentials
    Given user navigates to Register screen
    When user tries to register with valid credentials
    Then verify registration is successfull
