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
Feature: Allocate bed
  This feature makes it possible to allocate a patient to a bed.
  
  Background:
	Given that we are on a Hospital
	And with unique department name
	And with a patientID
	And I want to allocate a patient from that deparment to a bed

  @tag3
  Scenario: Patient allocated succesfully
    When I allocate patient to bed
    Then I get a message saying the patient was allocated to bed no x
    
  @tag4
  Scenario: No available beds
    When I allocate patient to bed
    Then I get a message that no beds are currently available
    
  @tag5
  Scenario: No patient with that ID in given department
    When I allocate patient to bed
    Then I get a message that no patient with given ID at department