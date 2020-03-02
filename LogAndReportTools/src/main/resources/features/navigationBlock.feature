Feature: Check all folders from navigation block at Yandex disk

  Background:
    Given User login to Yandex disk

  @navigationBlock
  Scenario: Check all folders in navigation block
    When User open Last Files block
    And User open Files block
    And User open Foto block
    And User open Albums block
    And User open Shared block
    And User open History block
    And User open Archive block
    And User open Garbage block
    Then Check that all folders in navigation block were opened
