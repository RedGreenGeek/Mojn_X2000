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
Feature: Move patients between departments
  This feature makes it possible to move patient between departments
  
  Background:
   	Given that we have a hospital with departments
   	And with a unique department names

  @tag3
  Scenario: moving a patient succesfully
    When I am entering a valid patient ID
    And I am entering an existing health care department
    Then I get a message with a statement that the patient has been moved succesfully
    
  @tag4
  Scenario Outline: moving a patient unsuccesfully cause to <cause>
    When I am entering a wrong <cause>
    Then I get an <error message> 
    
   Examples: 
      | cause		 				 | error message |
      | patient ID       | ID error			 |
      | patient admission| admit error	 |
