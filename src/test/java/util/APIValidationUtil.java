package util;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

public class APIValidationUtil {

    public static void validateStatusCode(Response response, int statusCode) throws AssertionError {
        try {
            response.then().statusCode(statusCode);
        } catch (AssertionError e) {
            throw e;
        }
    }

    public static void validateFieldIsNotEmpty(Response response, String fieldName) throws AssertionError {
        try {
            response.then().assertThat().body(fieldName, not(empty()));
        } catch (AssertionError e) {
            throw new AssertionError(e.getMessage());
        }
    }
}
