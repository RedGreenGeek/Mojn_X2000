#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Register Staff
  This feature makes it possible to add staff to a record.
  
  Background:
  Given that we are on a Hospital
  And with sufficient departments

  @tag1
  Scenario: Staff registered successfully.
    Given I am on the staff registration page
    When I enter sufficient staff data
    And click register staff
    Then I receive confirmation that the staff member is registred
    
  @tag2
  Scenario: Staff registered unsuccessfully
    Given I am on the registration page
    When I enter insufficient staff data
    And I hit register staff
    Then I receive an error message "insufficient staff data"
    