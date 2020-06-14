package testCases.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apiMethods.ApiMethods;
import io.restassured.response.Response;
import wdMethods.ProjectMethods;

public class UsersTest extends ProjectMethods {

    static ApiMethods api = new ApiMethods();
    static String id = null;
    static String state = null;
    static Response postResponse = null;

    private void setEndPointAndHeaders() {
        api.wsISetEndPoint("http://qainterview.merchante-solutions.com:3030/");
    }

    public void createUsers() {
        postResponse = api.wsIPostRequest("/users", reqUsers());
        id = api.wsIGetParameterValue(postResponse, "id");
        state = api.wsIGetParameterValue(postResponse, "address.State");
        api.wsIValidateStatusCode("201");
    }

    public void putUsers() {
        api.wsIPutRequest("/users/" + id, putReqUsers());
        api.wsIValidateStatusCode("200");
        api.wsIValidateExactParameterValue("name", 0, "UpdatedTest User");
        api.wsIValidateExactParameterValue("email", 0, "updatedtest@test.com");
        api.wsIValidateExactParameterValue("username", 0, "UpdatedUserName");
    }

    public void getUsers() {
        api.wsIGetResponse("/users/", id);
        api.wsIValidateStatusCode("200");
    }

    private String reqUsers() {
        return "{\n" + "    \"address\": {\n" + "      \"Zip\": \"33333\",\n" + "      \"State\": \"GA\",\n"
                + "      \"Phone\": \"123-123-1234\",\n" + "      \"Street\": \"123 Williams Rd\",\n"
                + "      \"City\": \"Norcross\"\n" + "    },\n" + "    \"name\": \"Test User\",\n" + "    \"id\": 0,\n"
                + "    \"email\": \"test@test.com\",\n" + "    \"username\": \"Test User\"\n" + "  }";

    }

    private String putReqUsers() {
        return "{\n" + "    \"address\": {\n" + "      \"Zip\": \"33333\",\n" + "      \"State\": \"GA\",\n"
                + "      \"Phone\": \"123-123-1234\",\n" + "      \"Street\": \"123 Williams Rd\",\n"
                + "      \"City\": \"Norcross\"\n" + "    },\n" + "    \"name\": \"UpdatedTest User\",\n"
                + "    \"id\": 0,\n" + "    \"email\": \"updatedtest@test.com\",\n"
                + "    \"username\": \"UpdatedUserName\"\n" + "  }";

    }

    @BeforeMethod
    public void beforeMethod() {
        test = startTestCase("Users - api", "CURD operations for Users");
    }

    @AfterMethod
    public void afterMethod() {
        endTestcase();
    }

    @Test
    public void test() {
        setEndPointAndHeaders();
        createUsers();
        getUsers();
        api.wsIValidateExactParameterValue("name", 0, "Test User");
        api.wsIValidateExactParameterValue("email", 0, "test@test.com");
        api.wsIValidateExactParameterValue("username", 0, "Test User");
        putUsers();
        getUsers();
        api.wsIValidateExactParameterValue("name", 0, "UpdatedTest User");
        api.wsIValidateExactParameterValue("email", 0, "updatedtest@test.com");
        api.wsIValidateExactParameterValue("username", 0, "UpdatedUserName");

    }

}
