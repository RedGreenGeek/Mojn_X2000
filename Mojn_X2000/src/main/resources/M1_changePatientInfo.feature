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
Feature: Change Patient Info

  Background:
	Given I have a patientID of a patient and I want to change their personal info

  @tag1
  Scenario: Succesful Change
    When I am entering a valid patientID
    And I am changing the given info to something valid
    Then I get a message that the change was succesful
  @tag2
  Scenario: Invalid patientID
    When I am entering invalid patientID
    Then I get a message that the patient does not exist
  @tag3
  Scenario: Illegal info change
    When I am entering a valid patientID
    And I am changing the info to something illegal
    Then I get a message that information entered was illegal 
    
  

    