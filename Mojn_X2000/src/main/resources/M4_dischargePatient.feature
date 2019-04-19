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
Feature: Discharge patients
  This feature makes it possible to remove a patient from a specific department at the hospital
  
  Background:
   	Given that we have a hospital with patients

  @tag3
  Scenario: removing a patient succesfully
    When I am entering an unique patient ID
    And The patient belongs to an unique department
    Then I get a message with a statement that the patient has been removed succesfully
    
 