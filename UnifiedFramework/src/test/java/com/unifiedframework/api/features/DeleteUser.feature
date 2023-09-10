@API @DeleteUser
Feature: Delete Users api, delete existing users

  @Success
  Scenario: Verify Delete Users api
    Given a user submits valid delete users request
    When a success response is received
    Then verify response time, size