Feature: DVLA website tests:

Scenario OUtline: verify the make color of a valid registraion vehicle matches with the stored values.
Given iam on the dvla website
And the registrationservice is running
And there are valid files present 
When a valid <registration number> is enterered from a file
Then the vehicle details are displayed
And matchs with the values in the file
<registrationnumber>
|SAZ1234|
|SAC4567|



Scenario: when a invalid registration number is entered
Given iam on the dvla website
And the registrationservice is running
And there are no valid files present 
when a invalid <registration Number> is entered from a file
Then the error message "enter a valid registration number"is displayed 


Scenario: When invalid registration number is entered
Given iam on the dvla website
And the registratinservice is running
when a new registration number is entered from a file
Then a warning message "registration number does not exists



