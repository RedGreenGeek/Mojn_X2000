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
Feature: Register Patient

	Background:
		Given that we are on a Hospital
		And with sufficient departments

  Scenario: Succesful Registration
    Given I am on the patient registration page
    And I am entering sufficient patient data
    When I hit "register"
    Then I get a message that the patient was registered succesfully
    
  Scenario: Insufficient Data Provided
    Given I am on the patient registration page
    And I am entering insufficient patient data
    When I hit "register"
    Then I get a message that additional information is needed
    
  

    