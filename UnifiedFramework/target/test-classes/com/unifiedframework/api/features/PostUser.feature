@API
Feature: Post Users api, create users

  @Success
  Scenario: Verify create Users api
    Given a user submits valid create users request
    When a success response is received
    Then verify response time, size
    And verify createUsers response schema