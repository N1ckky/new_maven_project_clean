Feature: New folder and word file creation, checking text in word file

  Background:
    Given User login to Yandex disk
    And User open Files block

  @createFolderAndWordFile
  Scenario: Create new folder in Files block, open it, create new Wold file inside and check it
  Open Word file and type in it text, than
  reopen it and check is text inside is the same (that you send to it before)
    When User create new folder with random name
    And User open created folder
    And User create new Word file
    And User switch to 1 tab in browser
    And User type text to Word file
    And User set name to Word file
    And User switch to 0 tab in browser
    And User open created Word file
    And User switch to 2 tab in browser
    Then The Word file must contains the same text
