package testCases.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apiMethods.ApiMethods;
import io.restassured.response.Response;
import wdMethods.ProjectMethods;

public class PostsTest extends ProjectMethods {
    static ApiMethods api = new ApiMethods();
    static String id = null;
    static Response postResponse = null;

    private void setEndPointAndHeaders() {
        api.wsISetEndPoint("http://qainterview.merchante-solutions.com:3030/");
    }

    public void createPosts() {
        postResponse = api.wsIPostRequest("/posts", reqPosts());
        id = api.wsIGetParameterValue(postResponse, "id");
        api.wsIValidateStatusCode("201");
    }

    public void putPosts() {
        api.wsIPutRequest("/posts/" + id, putReqPosts());
        api.wsIValidateStatusCode("200");
        String title = api.wsIGetParameterValue(postResponse, "title");
        String body = api.wsIGetParameterValue(postResponse, "body");
        api.wsIValidateExactParameterValue("title", 0, "Test Updated Title");
        api.wsIValidateExactParameterValue("body", 0, "Test updated Body");
    }

    public void getPosts() {
        api.wsIGetResponse("/posts/", id);
        api.wsIValidateStatusCode("200");
    }

    private String reqPosts() {
        return "{\n" + "            \"userId\": 0,\n" + "            \"id\": 0,\n"
                + "            \"title\": \"Test Title\",\n" + "            \"body\": \"Test Body\"\n" + "          }";

    }

    private String putReqPosts() {
        return "{\n" + "    \"userId\": 0,\n" + "    \"id\": \"" + id + "\",\n"
                + "    \"title\": \"Test Updated Title\",\n" + "    \"body\": \"Test updated Body\"\n" + "  }";

    }

    @BeforeMethod
    public void beforeMethod() {
        test = startTestCase("Posts - api", "CURD operations for Posts");
    }

    @AfterMethod
    public void afterMethod() {
        endTestcase();
    }

    @Test
    public void test() {
        setEndPointAndHeaders();
        createPosts();
        getPosts();
        api.wsIValidateExactParameterValue("title", 0, "Test Title");
        api.wsIValidateExactParameterValue("body", 0, "Test Body");
        putPosts();
        getPosts();
        api.wsIValidateExactParameterValue("title", 0, "Test Updated Title");
        api.wsIValidateExactParameterValue("body", 0, "Test updated Body");
    }
}
