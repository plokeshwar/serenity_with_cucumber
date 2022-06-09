@creating-reminder
Feature: As a busy tester, I should be able to create reminders, so that I can plan my day and not forget key tasks

  @single-reminder
  Scenario: Creating single reminder task
     Given Pravin is on create reminder landing page
     When he creates a reminder "Review TestCases"
     Then he should see "Review TestCases" added to the reminder list
     And he should see a total of 1 reminders added to the list

  @multiple-reminders
  Scenario: Creating multiple reminder tasks
    Given Pravin is on create reminder landing page
    When he creates a reminder "Review TestCases"
    And he creates a reminder "Automate TestCases"
    And he creates a reminder "Setup CI/CD"
    And he creates a reminder "Setup Notification"
    Then he should see a total of 4 reminders added to the list

  @multiple-datatable
  Scenario: Creating multiple reminders with datatables
    Given Pravin is on create reminder landing page
    When he creates following reminders
    | Review TestPlan A |
    | Review TestPlan B |
    | Review TestPlan C |
    | Review TestPlan D |
    Then he should see a total of 4 reminders added to the list

  @multiple-example
  Scenario Outline: Creating multiple reminders with examples
    Given Pravin is on create reminder landing page
    When he creates a reminder "<Reminder>"
    Then he should see "<Reminder>" added to the reminder list
    Then he should see a total of <Count> reminders added to the list
    Examples:
    | Reminder          | Count |
    | Review TestPlan A | 1     |
    | Review TestPlan B | 1     |
    | Review TestPlan C | 1     |


  @refresh
  Scenario: Verifying reminders are maintained in list, even on browser refresh
    Given Pravin is on create reminder landing page
    When he creates following reminders
    | Review TestPlan A |
    | Review TestPlan B |
    | Review TestPlan C |
    | Review TestPlan D |
    Then he should see a total of 4 reminders added to the list
    When Pravin refreshes the reminder landing page
    Then he should see a total of 4 reminders added to the list
