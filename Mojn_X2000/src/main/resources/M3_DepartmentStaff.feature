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
  Given that we have a Hospital with Departments
  And with departments

  @tag1
  Scenario: Staff added to department successfully.
    Given I am on the staff page
    When I add a staff member to a depart
    Then I receive confirmation that the staff member added to the department
    