package util;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import pojos.CreateABooking;
import pojos.CreateBookingResponse;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

public class BaseUtil {

    public static String base_directory;
    public static Logger logger;
    public static Properties configProperties;
    public Scenario message;
    public static StringWriter requestWriter;
    public static PrintStream requestCapture;
    public static Response response;

    public static CreateABooking createABooking;
    public static CreateBookingResponse createBookingResponse;
}
