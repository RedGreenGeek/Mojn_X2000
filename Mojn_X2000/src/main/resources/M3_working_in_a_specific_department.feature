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
Feature: List of Staff
  This feature makes it possible to get a list of staff working in a given department.
  
  Background:
	Given that we are on a Hospital
	And with departments
	And I want to see who is working in a specific department

  @tag3
  Scenario: Staff list Succesful.
    When I am entering a correct department name.
    Then I get a list of staff member for the specific department.
    
  @tag4
  Scenario: Staff list unsuccessfully
    When I am entering a incorrect department name.
    Then I get a message that the department is not found