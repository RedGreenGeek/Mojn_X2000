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
	And with a Staff Registery
	And I am on the staff registration page

  @tag3
  Scenario: Staff Succesful Registration
    When I am entering sufficient staff data
    Then I get a message that the staff was registered succesfully
    
  @tag4
  Scenario: Staff registered unsuccessfully
    When I am entering insufficient staff data
    Then I get a message
