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
Feature: Change Password
  This feature makes it possible to change a password for a staff
  
  Background:
   	Given that we are on a Hospital
   	And with a staff ID & a Password

  @tag3
  Scenario: Password changed succuesfully
    When I am entering the staff-ID, old password and new wanted password twice
    Then I get a message that my password was correctly changed
  @tag4
  Scenario: Password not changed as old password was not known
    When I am entering the staff-ID, wrong old password and new wanted password twice
    Then I get a message that my password has not been changed, as I did not know my old password
   
  @tag5
  Scenario: Password not changed as new password not correctly repeated
    When I am entering the staff-ID, old password, new wanted password and a wrong new wanted password
    Then I get a message that my password has not been changed, as I did not repeat my new password correctly

	@tag6
  Scenario: Password not added, staff ID does not exist
    When I am entering a wrong staff id, wanted password twice and old password
    Then I get a message that my password was not added, as the Staff does not exist
