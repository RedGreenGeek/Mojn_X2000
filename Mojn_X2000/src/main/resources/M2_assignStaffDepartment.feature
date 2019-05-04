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
Feature: Assign Staff Department
  This feature makes it possible to assign staff to a department.
  
  Background:
	Given that we are on a hospital
	And given a department name
	And given staff info
		
  @tag1
  Scenario: Add staff to department successfully
    When I am entering correct info 
    And I am entered a correct department name
    Then I get a message that the staff was added sucessfully
    
  @tag2
  Scenario: Invalid department name
    When I am entering invalid department name
    Then I recieve an error message
    
  @tag3
  Scenario: Invalid info
    When I am entering invalid info
    Then I recieve a error message

