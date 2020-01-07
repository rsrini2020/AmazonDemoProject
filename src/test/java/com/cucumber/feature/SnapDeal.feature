Feature: Amazon application smoke test cases

Scenario: Amazon Home page validation
When User Launch the amazon application
Then User verify the amazon logo present in the page
And User verify the amazon page title 
And User should see search feild present in the page

Scenario Outline: Amazon search functionality validations
When User enter "<Search>" in the search feild
And User click on search button
Then User should see the "<Search>" in the search result page header
Examples:
|Search|
|Mobile|
|Laptop|

Scenario Outline: Amazon Menu navigation functionality validation
When User click on Amazon Hamburger menu bar
And User click on main menu "<MainMenu>"
And User click on sub menu "<SubMenu>"
Then User should see the "<Result>" in the landing page

Examples:
|MainMenu				|SubMenu		|Result		|
|Men's Fashion	|Shirts			|SHIRT		|
|Women's Fashion|Watches		|WATCHES	|

