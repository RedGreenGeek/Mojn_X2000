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
	And I am an ICT officer
		
  @tag1
  Scenario: Change a staff member succesfully
    When I am entering sufficient search data
    Then I can change the wanted parameters to something valid
    And Load the staff member back into the database
    
  @tag2
  Scenario: Invalid search
    When I am entering insufficient search data
    Then I recieve an error message that the employee does not exist
    
  @tag2
  Scenario: Invalid changed parameters
    When I try invalid changes on a staff
    Then I recieve an error message saying that it is invalid

