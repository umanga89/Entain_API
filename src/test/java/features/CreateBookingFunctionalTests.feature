Feature: Create Bookings Functional Tests

  @api_functional
  Scenario: Verify user is able to create a booking successfully when create booking request is sent with all applicable parameters
    Given User populates the end point to create a new booking
    When User sends create booking request with given parameters
      | parameter       | value               |
      | firstname       | firstTest_          |
      | lastname        | lastTest_           |
      | totalprice      | 102                 |
      | depositpaid     | true                |
      | checkin         | 2023-05-01          |
      | checkout        | 2023-05-07          |
      | additionalneeds | Breakfast and Lunch |
    Then User should be able to see success request with status code as 200
    And User should be able to see "bookingid" with following parameters in response body
      | parameter       | value               |
      | firstname       | firstTest_          |
      | lastname        | lastTest_           |
      | totalprice      | 102                 |
      | depositpaid     | true                |
      | checkin         | 2023-05-01          |
      | checkout        | 2023-05-07          |
      | additionalneeds | Breakfast and Lunch |
    And User populates the end point to get a booking request
    And User should be able to send a get booking request with "bookingid"
    And User should be able to see success request with status code as 200
    And User should be able to see booking information with following parameters in response body
      | parameter       | value               |
      | firstname       | firstTest_          |
      | lastname        | lastTest_           |
      | totalprice      | 102                 |
      | depositpaid     | true                |
      | checkin         | 2023-05-01          |
      | checkout        | 2023-05-07          |
      | additionalneeds | Breakfast and Lunch |

  @TODO
    #Can write similar test for all request body parameters
  Scenario: Verify proper error message is shown when create a booking request is sent without firstname parameter in request body

  @TODO
  Scenario:Verify proper error message is shown when create a booking request is sent with empty first name parameter in request body

  @TODO
  Scenario:Verify proper error message is shown when create a booking request is sent with null value for first name parameter in request body

  @TODO
  Scenario: Verify maximum character length for first name in create a booking request body

  @TODO
  Scenario: Verify proper error message is shown when total parameter has decimal points in create a booking request body

  @TODO
  Scenario: Verify proper error message is shown when total parameter has a string value in create a booking request body

  @TODO
      #Seems like default value is set to true for depositpaid in server side if value is invalid
  Scenario: Verify proper error message is shown when depositpaid parameter contains a string value (i.e. "123")

  @TODO
  Scenario Outline: Verify checkin parameter accepts date in different formats
    Examples:
      | MM-DD-YYYY |
      | DD.MM.YYYY |
      | YYYY/DD/MM |

  @TODO
  Scenario: Verify checkin date parameter cannot be greater than checkout date parameter value