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
Feature: Register Staff
  This feature makes it possible to add staff to a record.
  
  Background:
	Given that we are on a hospital
	And the hospital contain staff in departments

  @tag1
  Scenario: Succesful Registration of Clerk
    When I am entering sufficient clerk data
    Then I get a message that the clerk was registered succesfully
    
  @tag2
  Scenario: Succesful Registration of Nurse
    When I am entering sufficient nurse data
    Then I get a message that the nurse was registered succesfully
    
  @tag3
  Scenario: Succesful Registration of Doctor
    When I am entering sufficient doctor data
    Then I get a message that the doctor was registered succesfully
    
  @tag4
  Scenario: Succesful Registration of ICTOfficer
    When I am entering sufficient ictofficer data
    Then I get a message that the ictofficer was registered succesfully
    
  @tag5
  Scenario: Staff Registered Unsuccessfully
    When I am entering insufficient staff data
    Then I get a message
