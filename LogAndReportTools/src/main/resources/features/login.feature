Feature: Login to Yandex
  as a yandex user with valid and invalid credentials

  Background:
    Given User is at login page

  @login
  Scenario: Successful login to yandex with valid credentials
    Given User has valid credentials
    When User enter login
    And User enter password
    And User click login button
    Then User sees main page

  @negativeLogin
  Scenario Outline: Unsuccessful login to yandex with invalid credentials
    Given User has invalid credentials
    When User enter <login> login
    And User enter <password> password
    And User click login button
    Then User sees error message

    Examples:
      | login       | password       |
      | wrong_login | wrong_password |
      | valid_login | wrong_password |
      | wrong_login | valid_password |

  @negativeLoginDT
  Scenario Outline: Login to yandex with different password
    Given Users with invalid credentials
      | nickTestQA | <password>   |

    When User enter login
    And User enter password
    And User click login button
    Then User sees error message

    Examples:
      | password       |
      | wrong_password |
      | wrong_password2 |
