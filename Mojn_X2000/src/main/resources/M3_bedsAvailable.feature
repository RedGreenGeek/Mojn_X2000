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
Feature: DepartmentStaff
  This feature makes it possible to add staff to a Department.
  
  Background:
  Given a Hospital
  And with operational IP_department
  Given I am on the In-Patient Department page

  @tag1
  Scenario: BedsAvailable reported successfully.
    Given That beds are available
    When I ask for the number of beds available
    Then the number of beds available is returned.
    
  @tag2
  Scenario: BedsAvailable reported unsuccesfully
    Given That beds are not available
    When I request the beds available
    Then the string "No beds available is returned".