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
Feature: Move patients between beds
  This feature makes it possible to move patient between beds
  
  Background:
   	Given that we have a hospital with indepartments
   	And with a unique department names

  @tag3
  Scenario: moving a patient succesfully between beds
    When I am writing a valid patient ID
    And I am entering an existing indepartment
    And I am entering a non occupied bedNo
    Then I get a return message with the statement that the patient was moved succesfully
    
  @tag4
  Scenario: moving a patient unsuccesfully between beds cause to invalid patient ID
    When I am writing an invalid patient ID
    Then I get an error message that the move between beds was unsuccesful
    
  @tag5
  Scenario: moving a patient unsuccesfully cause to non existent or multiple departments
    When I am trying to move to a non existent department or multiple departments
    Then I get a message that the move between beds was unsuccesful
    
  @tag6
  Scenario: moving a patient unsuccesfully because the department isn't an indepartment
  When I am trying to move to a department that isn't an indepartment
  Then I get a message that the move between beds was unsuccesful because the department isn't an indepartment