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
import util.APIHelperUtil;
import util.APIValidationUtil;
import util.BaseUtil;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateABookingSteps extends BaseUtil {

    public CreateABookingSteps() {
        logger = LogManager.getLogger(CreateABookingSteps.class.getName());
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
    }

    @Given("User populates the end point to create a new booking")
    public void user_populates_the_end_point_to_create_a_new_booking() throws Exception {
        try {
            //Set Base URI
            RestAssured.baseURI = configProperties.getProperty("base.path");
            //Set base path
            RestAssured.basePath = APIHelperUtil.CREATE_A_BOOKING;
            BaseUtil.logger.log(Level.INFO, "Set Create A Booking request URL as " + configProperties.getProperty("base.path") + APIHelperUtil.CREATE_A_BOOKING);
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occured =>\n" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @When("User sends create booking request with given parameters")
    public void user_sends_create_booking_request_with_given_parameters(DataTable dataTable) throws Exception {
        try {
            List<List<String>> listOfData = dataTable.asLists(String.class);
            //Initialise a POJO of Add a Meme request to be used in request body
            CreateABooking createABookingObj = APIHelperUtil.buildAddAMemeRequest(listOfData);
            createABooking = createABookingObj;
            response = given().log().all().header("Content-type", "application/json").body(createABookingObj).when().post();
            requestCapture.flush();
            BaseUtil.logger.log(Level.INFO, "Create a booking request has been sent");
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occurred =>\n" + e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
            throw new Exception(e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
        }
    }

    @Then("User should be able to see {string} with following parameters in response body")
    public void user_should_be_able_to_see_following_parameters_in_response_body(String paramName, DataTable dataTable) throws Exception {
        try {
            List<List<String>> listOfData = dataTable.asLists(String.class);
            createBookingResponse = response.as(CreateBookingResponse.class);
            //validating response parameters and their value
            APIValidationUtil.validateFieldIsNotEmpty(response, listOfData.get(1).get(0));
            Assert.assertEquals(createBookingResponse.getBooking().getFirstname(), createABooking.getFirstname(), "Mismatch in firstname");
            Assert.assertEquals(createBookingResponse.getBooking().getLastname(), createABooking.getLastname(), "Mismatch in lastname");
            Assert.assertEquals(createBookingResponse.getBooking().getTotalprice(), createABooking.getTotalprice(), "Mismatch in totalprice");
            Assert.assertEquals(createBookingResponse.getBooking().getDepositpaid(), createABooking.getDepositpaid(), "Mismatch in deposit paid");
            Assert.assertEquals(createBookingResponse.getBooking().getBookingdates().getCheckin(), createABooking.getBookingdates().getCheckin(), "Mismatch in checkin");
            Assert.assertEquals(createBookingResponse.getBooking().getBookingdates().getCheckout(), createABooking.getBookingdates().getCheckout(), "Mismatch in checkout");
            Assert.assertEquals(createBookingResponse.getBooking().getAdditionalneeds(), createABooking.getAdditionalneeds(), "Mismatch in additionalneeds");
        } catch (Exception e) {
            BaseUtil.logger.log(Level.ERROR, "ERROR Occurred =>\n" + e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
            throw new Exception(e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
        }
    }
}
