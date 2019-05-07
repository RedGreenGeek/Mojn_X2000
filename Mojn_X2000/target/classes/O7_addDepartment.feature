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
Feature: Add a department to hospital
  This feature makes it possible to add a department to a hospital
  
  Background:
   	Given that we have a hospital

  @tag1
  Scenario: Add a outPatient department
    When a new outPatient department
    Then I get message that the department was added
    
  @tag2
  Scenario: Add a inPatient department
    When a new inPatient department
    Then I get message that the department was added
    
  @tag3
  Scenario: Add a admin department
    When a new admin department
    Then I get message that the department was added
  
  @tag4
  Scenario: Invalid department type.
    When I try to add an invalid department type
    Then I get message that no department was added
    
  @tag5
  Scenario: Not an integer as maxBed
    When I try to add a inPatient but maxBed is not an integer
    Then I get a message that maxBeds must be an integer
    
  @tag6
  Scenario: Name already in use
    When I try to add a department with a name already in use
    Then I get a message that the name must be unique