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
  Scenario Outline: moving a patient succesfully between beds
    When I am writing a valid patient ID
    And I am entering a <bed>
    Then I get a good <return message>
    
   Examples: 
      | bed				       | return message       |
      | Diffrent bed no  | Moved to diffrent bed|
      | Same bed no		   | Moved to same bed 	  |
    
  @tag4
  Scenario Outline: moving a patient unsuccesfully between beds
    When I am writing an <invalid info>
    Then I get an <error info>
    
    Examples: 
      | invalid info | error info       |
      | not int bed  | not int error		|
      | Out patient  | Out patient error|
      | Wrong ID     | Wrong ID error   |
      | bed over max | bed max error		|
      

