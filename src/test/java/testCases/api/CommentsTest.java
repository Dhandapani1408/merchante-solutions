package testCases.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apiMethods.ApiMethods;
import io.restassured.response.Response;
import wdMethods.ProjectMethods;

public class CommentsTest extends ProjectMethods {

    static ApiMethods api = new ApiMethods();
    static String id = null;
    static Response postResponse = null;

    private void setEndPointAndHeaders() {
        api.wsISetEndPoint("http://qainterview.merchante-solutions.com:3030/");
    }

    public void createComments() {
        postResponse = api.wsIPostRequest("/comments", reqComments());
        id = api.wsIGetParameterValue(postResponse, "id");
        api.wsIValidateStatusCode("201");
    }

    public void putComments() {
        api.wsIPutRequest("/comments/" + id, putReqComments());
        api.wsIValidateStatusCode("200");
        api.wsIValidateExactParameterValue("body", 0, "UPDATED_JAVA_SELENIUM_API");
        api.wsIValidateExactParameterValue("email", 0, "updatedtest@test.com");
        api.wsIValidateExactParameterValue("name", 0, "UpdatedTestName");
    }

    public void getComments() {
        api.wsIGetResponse("/comments/", id);
        api.wsIValidateStatusCode("200");
    }

    private String reqComments() {
        return "{\n" + "    \"name\": \"TestName\",\n" + "    \"postId\": 0,\n" + "    \"id\": 0,\n"
                + "    \"body\": \"JAVA_SELENIUM_API\",\n" + "    \"email\": \"test@test.com\"\n" + "  }";

    }

    private String putReqComments() {
        return "{\n" + "    \"name\": \"UpdatedTestName\",\n" + "    \"postId\": 0,\n" + "    \"id\": " + id + ",\n"
                + "    \"body\": \"UPDATED_JAVA_SELENIUM_API\",\n" + "    \"email\": \"updatedtest@test.com\"\n"
                + "  }";

    }

    @BeforeMethod
    public void beforeMethod() {
        test = startTestCase("Comments - api", "CURD operations for Comments");
    }

    @AfterMethod
    public void afterMethod() {
        endTestcase();
    }

    @Test
    public void test() {
        setEndPointAndHeaders();
        createComments();
        getComments();
        api.wsIValidateExactParameterValue("body", 0, "JAVA_SELENIUM_API");
        api.wsIValidateExactParameterValue("email", 0, "test@test.com");
        api.wsIValidateExactParameterValue("name", 0, "TestName");
        putComments();
        getComments();
        api.wsIValidateExactParameterValue("body", 0, "UPDATED_JAVA_SELENIUM_API");
        api.wsIValidateExactParameterValue("email", 0, "updatedtest@test.com");
        api.wsIValidateExactParameterValue("name", 0, "UpdatedTestName");
    }

}
