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
Feature: Beds Available
  This feature makes it possible to check available beds in a department
  
  Background:
   	Given that we are on a department
   	And with a unique department name

  @tag3
  Scenario: beds available checked succesfully - beds available
    When I am entering the request
    Then I get a message with a statement that there is available beds
    
  @tag4
  Scenario: beds available checked succesfully - no beds available
    When I am entering the request
    Then I get a message with a statement that there is no available beds