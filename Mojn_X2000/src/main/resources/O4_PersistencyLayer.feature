##Author: your.email@your.domain.com
##Keywords Summary :
##Feature: List of scenarios.
##Scenario: Business rule through list of steps with arguments.
##Given: Some precondition step
##When: Some key actions
##Then: To observe outcomes or validation
##And,But: To enumerate more Given,When,Then steps
##Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
##Examples: Container for s table
##Background: List of steps run before each of the scenarios
##""" (Doc Strings)
##| (Data Tables)
##@ (Tags/Labels):To group Scenarios
##<> (placeholder)
##""
### (Comments)
##Sample Feature Definition Template
#@tag
#Feature: Database System
#
#  @tag1
#  Scenario: Database Startup
#    Given a hospital using the management system
#    When some staff start the system on their computer
#    Then the system is connected to the database
#    
#  @tag2
#  Scenario: Writing a patient
#    Given a hospital using the management system
#    When some staff adds a patient
#    Then the patient is stored in the database  
#    
#  @tag3
#  Scenario: Writing a staff
#    Given a hospital using the management system
#    And a staff in the system
#    When some staff adds a staff
#    Then the staff is stored in the database    
#    
#  @tag4
#  Scenario: Writing a department
#    Given a hospital using the management system
#    When some staff adds a department
#    Then the department is stored in the database    
#       
#  @tag5
#  Scenario: Loading patients
#    Given a hospital using the management system
#    And a database with patient information
#    When some staff start the system on their computer
#    Then all patients are loaded from the database
#
#  @tag6
#  Scenario: Loading staff
#    Given a hospital using the management system
#    And a database with staff information
#    When some staff start the system on their computer
#    Then all staff are loaded from the database
#
#  @tag7
#  Scenario: Loading departments
#    Given a hospital using the management system
#    And a database with department information
#    When some staff start the system on their computer
#    Then all departments are loaded from the database
#    And the departments contains pointers to patients
#    And the departments contains pointers to staff
#    
#  @tag8
#  Scenario: Rebooting system
#    Given a hospital using the management system
#    And a database with all hospital information
#    When some staff start the system on their computer
#    Then the entire hospital is reconstructed to match the last state
