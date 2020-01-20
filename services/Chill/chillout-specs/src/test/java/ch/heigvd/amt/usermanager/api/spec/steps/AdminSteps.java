package ch.heigvd.amt.usermanager.api.spec.steps;

import ch.heigvd.amt.usermanager.ApiException;
import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.ProductApi;
//import ch.heigvd.amt.usermanager.api.dto.JwtResponse;
import ch.heigvd.amt.usermanager.api.dto.Product;
import ch.heigvd.amt.usermanager.api.spec.helpers.Environment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.*;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class AdminSteps {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client;
    private Environment environment;
    private ProductApi productApi;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private String timestamp;
    private Gson gson;

    private Product productPayload;
    private String token;

    public AdminSteps(Environment environment){
        this.environment = environment;
        this.productApi = environment.getProductApi();
        this.lastApiResponse = environment.getLastApiResponse();
        this.client = this.environment.getHttpClient();
        this.token = environment.getToken();
        this.productPayload = new Product();
    }

    @Given("^there is a Product server$")
    public void thereIsAProductServer() throws Throwable {
        assertNotNull(productApi);
    }

    @Given("^I have an admin login payload$")
    public void iHaveALoginPayload() throws Throwable {
        String user = "adam.zouari@heig-vd.ch";
        String password = "azouari";
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFtLnpvdWFyaUBoZWlnLXZkLmNoIiwiaXNCbG9ja2VkIjowLCJpc0FkbWluIjoxLCJleHAiOjE1Nzk0ODg3OTcsImlhdCI6MTU3OTQ3MDc5N30.ur_cySvkACAs2pRiIY_QFaCuF7WcFt8KDsHLdWmzh1DGwhNVNncCDHWFCmsgBmi58hVkUUaUPX6CBvJS5x2sZQ";
//        this.productApi.getApiClient().setApiKey("Bearer " + getJwtTokenByAuthenticatingToUsersApi(user, password));
       this.productApi.getApiClient().setApiKey("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFtLnpvdWFyaUBoZWlnLXZkLmNoIiwiaXNCbG9ja2VkIjowLCJpc0FkbWluIjoxLCJleHAiOjE1Nzk0ODg3OTcsImlhdCI6MTU3OTQ3MDc5N30.ur_cySvkACAs2pRiIY_QFaCuF7WcFt8KDsHLdWmzh1DGwhNVNncCDHWFCmsgBmi58hVkUUaUPX6CBvJS5x2sZQ");
    }

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

//    @And("^Admin token is returned in the body$")
//    public void tokenIsReturnedInTheBody() throws Throwable {
//        JsonObject jsonObject = gson.fromJson((JsonElement) lastApiResponse.getData(), JsonObject.class);
//        assertNotNull(jsonObject);
//        assertNotEquals("", jsonObject);
//    }

    @When("^I POST it to the /products endpoint$")
    public void iPOSTItToTheProductsEndpoint() throws Throwable {
        productApi.getApiClient().addDefaultHeader("Authorization", "Bearer "+token);

        try {
            lastApiResponse = productApi.createUserWithHttpInfo(productPayload);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }
//
//    @When("^I do a GET on /users endpoint$")
//    public void iDoAGETOnUsersEndpoint() throws Throwable {
//
//        try {
//            lastApiResponse = usersApi.getUsersWithHttpInfo(1,20);
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//
//    @And("^The list is returned in the body$")
//    public void theListIsReturnedInTheBody() throws Throwable {
//        assertNotEquals(lastApiResponse.getData().getClass(), List.class);
//    }


    private String getJwtTokenByAuthenticatingToUsersApi(String username, String password) throws ApiException {
        String s = "{ \"email\": \""+ username +"\", \"password\": \""+ password +"\"}";
        RequestBody body = RequestBody.create(s, JSON);
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/authenticate")
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .post(body)
                .build();
        System.out.println(request.body());
        try (Response response = client.newCall(request).execute()) {
            String jwtToken = response.body().toString();
            JsonObject jsonObject = gson.fromJson(jwtToken, JsonObject.class);
            return jsonObject.get("token").getAsString();
        } catch (NullPointerException | IOException e) {
            throw new ApiException("Could not retrieve jwt token from the authentications endpoint");
        }
    }
}
