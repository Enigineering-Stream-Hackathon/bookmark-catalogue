Feature: As an User, I want to create short url

    Scenario: Create short url with expiry date from long url
      Given an user wants to create a short url for "https://longurl.com" which expires on 2020-10-10
      When the user clicks on generate short url
      Then user shall get the short url