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
Feature: Patient admission
  This feature makes it possible to add patient to a specific department at the hospital
  
  Background:
   	Given that we have a hospital with departments
   	And with a unique department names

  @tag3
  Scenario: adding a patient succesfully
    When I am entering valid patient data
    And I am entering a existing department
    Then I get a message with a statement that the patient has been added succesfully
    
  @tag4
  Scenario: adding a patient unsuccesfully cause to invalid patient data
    When I am entering invalid patient data
    Then I get a message with the statement that the patient hasn't been added unsuccesfully cause to invalid patient data
    
  @tag5
  Scenario: adding a patient unsuccesfully cause to non existent department
    When I am entering a non existent department
    Then I get a message with the statement that the patient hasn't been added unsuccesfully cause to non existent department