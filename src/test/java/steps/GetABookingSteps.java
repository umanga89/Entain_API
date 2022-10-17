package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import pojos.CreateABooking;
import pojos.CreateBookingResponse;
import pojos.GetABooking;
import util.APIHelperUtil;
import util.APIValidationUtil;
import util.BaseUtil;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetABookingSteps extends BaseUtil {

    ResponseSpecification resSpec;

    public GetABookingSteps() {
        logger = LogManager.getLogger(GetABookingSteps.class.getName());
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
    }

    @Given("User populates the end point to get a booking request")
    public void user_populates_the_end_point_to_get_a_new_booking() throws Exception {
        try {
            //Set Base URI
            RestAssured.baseURI = configProperties.getProperty("base.path");
            //Set base path
            RestAssured.basePath = APIHelperUtil.GET_A_BOOKING+createBookingResponse.getBookingid();
            BaseUtil.logger.log(Level.INFO, "Set Get A Booking request URL as " + configProperties.getProperty("base.path") + APIHelperUtil.GET_A_BOOKING+createBookingResponse.getBookingid());
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occured =>\n" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Then("User should be able to send a get booking request with {string}")
    public void user_should_be_able_to_send_a_get_booking_request_with(String paramName) throws Exception{
        try {
            response = given().log().all().header("Content-type", "application/json").when().get();
            requestCapture.flush();
            BaseUtil.logger.log(Level.INFO, "Get a booking request has been sent");
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occurred =>\n" + e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
            throw new Exception(e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
        }
    }

    @Then("User should be able to see booking information with following parameters in response body")
    public void user_should_be_able_to_see_booking_information_with_following_parameters_in_response_body(DataTable dataTable) throws Exception{
        try {
            List<List<String>> listOfData = dataTable.asLists(String.class);
            GetABooking getABooking = response.as(GetABooking.class);
            //validating response parameters and their value
            APIValidationUtil.validateFieldIsNotEmpty(response, listOfData.get(1).get(0));
            Assert.assertEquals(getABooking.getFirstname(), createABooking.getFirstname(), "Mismatch in firstname");
            Assert.assertEquals(getABooking.getLastname(), createABooking.getLastname(), "Mismatch in lastname");
            Assert.assertEquals(getABooking.getTotalprice(), createABooking.getTotalprice(), "Mismatch in totalprice");
            Assert.assertEquals(getABooking.getDepositpaid(), createABooking.getDepositpaid(), "Mismatch in deposit paid");
            Assert.assertEquals(getABooking.getBookingdates().getCheckin(), createABooking.getBookingdates().getCheckin(), "Mismatch in checkin");
            Assert.assertEquals(getABooking.getBookingdates().getCheckout(), createABooking.getBookingdates().getCheckout(), "Mismatch in checkout");
            Assert.assertEquals(getABooking.getAdditionalneeds(), createABooking.getAdditionalneeds(), "Mismatch in additionalneeds");
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occurred =>\n" + e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
            throw new Exception(e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
        }
    }


}
