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
Feature: Add Password
  This feature makes it possible to add a password for a staff, who has never had a password before.
  
  Background:
   	Given that we are on a hospital
   	And with a staff ID & a password

  @tag3
  Scenario: Password added succuesfully
    When I am entering the staff-ID and wanted password twice
    Then I get a message that my password was correctly added to the system
  @tag4
  Scenario: Password not added, repeated password wrong
    When I am entering the staff-ID, wanted password and a wrong wanted password
    Then I get a message that my password was not added, as I did not repeat my password correctly
  @tag4
  Scenario: Password not added, a password for that Staff already exists
    When I am entering the staff id and wanted password twice
    Then I get a message that my password was not added, as the Staff already has a password
 
