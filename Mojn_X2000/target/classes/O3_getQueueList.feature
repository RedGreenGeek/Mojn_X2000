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
Feature: Get Queue List
  This feature makes it possible to get a queue list of a out patient department
  
  Background:
   	Given that we have a hospital with departments
   	And with a unique out patient department name

  @tag1
  Scenario: Getting a queue list successfully
    When I am entering unique department name of a out patient department
    Then I get a list of the current queue
    
  @tag2
  Scenario: Invalid department name
    When I am entering non unique department name
    Then I get a error message
    
  @tag3
  Scenario: Invalid department type
    When I am entering unique department name of non out patient department
    Then I get a error message
  
    

    
  