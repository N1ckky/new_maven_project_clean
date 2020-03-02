Feature: Move file to garbage

  Background:
    Given User login to Yandex disk
    And User open Files block

  @garbage
  Scenario: Delete file to garbage and clear garbage
    When User delete file to garbage
    Then Check that file is not in current folder
    When User open Garbage block
    Then Check that file is in current folder
    And User clear garbage
    Then Check that file not in garbage
