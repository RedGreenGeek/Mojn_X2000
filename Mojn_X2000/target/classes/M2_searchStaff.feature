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
Feature: Change Staff Information
  This feature makes it possible to change staff information.
  
  Background:
	Given that we are on a hospital
	And there is registered staff
		
  @tag1
  Scenario: Search for staff members succesfully
    When I am entering search data matching staff
    Then I get a list over my search results
    
  @tag2
  Scenario: Invalid search
    When I am entering search data not matching staff
    Then I recieve a message that the employee does not exist
    


