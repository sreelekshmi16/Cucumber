@API @UpdateUser
Feature: Put Users api, update existing users

  @Success
  Scenario: Verify Update Users api
    Given a user submits valid update users request
    When a success response is received
    Then verify response time, size
    And verify updateUsers response schema
    And verify the result using get api