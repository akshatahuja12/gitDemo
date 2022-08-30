Feature: Login into Application

Scenario Outline: Positive test validating login
Given Initialize the browser with chrome
And Navigate to "https://rahulshettyacademy.com" Site
And Click on login link in homepage to land on secure sign in page
When User enters <username> and <password> and clicks on login button
Then Verify user is succesfully logged in
And Close the browser

Examples:
|username						|password				|
|test99@gmail.com   |123456					|
|test9@gmail.com    |12345					|



