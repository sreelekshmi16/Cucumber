@API @GetUsers
Feature: Get Users api, list all activities

  @Success
  Scenario: Verify Get Users api
    Given a user submits valid get users request
    When a success response is received
    Then verify response time, size
    And verify getUsers response schema