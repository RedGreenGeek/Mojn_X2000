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
Feature: Get Next In Queue
  This feature makes it possible to get the next patient in a queue
  
  Background:
   	Given that we have a hospital with departments
   	And with a unique out patient department name
   	And we ask for next person in queue

  @tag4
  Scenario: Getting a patient successfully
    When I am entering unique department name of a out patient department
    Then I get the next patient in queue
    
  @tag5
  Scenario: Invalid department names
    When I am entering non unique department names
    Then I get an error message
    
  @tag6
  Scenario: Invalid department type
    When I am entering a unique department name of non out patient department
    Then I get an error message
  