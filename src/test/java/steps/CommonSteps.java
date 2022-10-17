package steps;

import io.cucumber.java.en.Then;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import util.APIValidationUtil;
import util.BaseUtil;

import java.io.PrintStream;
import java.io.StringWriter;

public class CommonSteps extends BaseUtil {

    public CommonSteps() {
        logger = LogManager.getLogger(CommonSteps.class.getName());
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
    }

    @Then("User should be able to see success request with status code as {int}")
    public void i_should_see_response_with_status_code_as(Integer statusCode) throws AssertionError {
        try {
            APIValidationUtil.validateStatusCode(response, statusCode);
            BaseUtil.logger.log(Level.INFO,"Status code validation is successful");
        } catch (AssertionError e) {
            logger.log(Level.ERROR, "Status code mismatch");
            logger.log(Level.ERROR, e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
            throw new AssertionError(e.getMessage() + "\n\nActual Response body is " + response.asString() + "\n");
        }
    }
}
