

Feature: Service tests


Scenario: Get request on a directory
Given I sent a getrequest to registrationservice on a directory
THen I get 200 as status code 
And valid files count is displayed

Scenario: Get request on a directory without valid files
given I sent a getrequest to registrationservice on a directory with out valid files
Then I get 200  as status code 
And valid files count is zero

Scenario: Get request on a directory without files
given I sent a getrequest to registrationservice on a directory with out files
Then I get 400 as status code 
And error message " directory contaions no files"

Scenario: Get request with valid registration number
given I sent a getrequest to registriaonservice with a valid registration number
Then I get 200 status code 
And the make and color of the vehicle are displayed


Scenario: Get request with invalid format registration number 
Given I sent a getRequest to registrationservice with invalid format registration number
Then I get 400 as status code
And "enter a registration number in valid format " message is displayed

Scenario: Get request with registration number does not exists
Given I sent a getRequest to registrationservice with a registration number does not exist
Then I get 400 as status code
ANd " registration number does not exists" message is displayed 



If time permits, I can implement the service tests





