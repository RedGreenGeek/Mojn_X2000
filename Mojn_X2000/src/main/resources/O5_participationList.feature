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
Feature: Get Participation List
  This feature makes it possible to get a participation list
  
  Background:
   	Given that we have a hospital with departments

  @tag1
  Scenario: Getting a participation list of entire hospital
    When I am requesting a partipation list of entire hospital
    And I choose what data I would like to receive
    Then I get a csv containing information of all patients
    
  @tag2
  Scenario: Getting participation list of certain department
    When I am requesting a partipation list of a given department
    Then I get a csv containing information of patients from that department
    
  @tag3
  Scenario: Invalid department name
    When I am entering a non unique department name
    Then I get message saying there was an error
  