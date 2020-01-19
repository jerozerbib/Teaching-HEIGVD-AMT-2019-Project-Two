package ch.heigvd.amt.usermanager.api.spec.steps;

import ch.heigvd.amt.usermanager.ApiClient;
import ch.heigvd.amt.usermanager.ApiException;
import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.AuthenticateApi;
import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.dto.JwtRequest;
import ch.heigvd.amt.usermanager.api.dto.JwtResponse;
import ch.heigvd.amt.usermanager.api.dto.UserInput;
import ch.heigvd.amt.usermanager.api.dto.UserOutput;
import ch.heigvd.amt.usermanager.api.spec.helpers.Environment;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AdminSteps {

    private Environment environment;
    private AuthenticateApi authApi;
    private UsersApi usersApi;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private String timestamp;

    private String token;
    private UserInput userPayload;
    private JwtRequest loginRequest;

    public AdminSteps(Environment environment){
        this.environment = environment;
        this.authApi= environment.getAuthApi();
        this.usersApi= environment.getUsersApi();
        this.lastApiResponse = environment.getLastApiResponse();
        this.token = environment.getToken();
        this.userPayload = new UserInput();
    }

    @Given("^there is a Users server$")
    public void thereIsAUsersServer() throws Throwable {
        assertNotNull(authApi);
        assertNotNull(usersApi);
    }

    @Given("^I have an admin login payload$")
    public void iHaveALoginPayload() throws Throwable {
        loginRequest = new JwtRequest();
        loginRequest.setEmail("adam.zouari@heig-vd.ch");
        loginRequest.setPassword("azouari");

    }

    @When("^I POST the admin login payload to the /authenticate endpoint$")
    public void iPOSTItToTheAuthenticateEndpoint() throws Throwable {
        try {
            lastApiResponse = authApi.createAuthenticationTokenWithHttpInfo(loginRequest);
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


    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^Admin token is returned in the body$")
    public void tokenIsReturnedInTheBody() throws Throwable {
        token = ((JwtResponse)lastApiResponse.getData()).getToken();
        assertNotNull(token);
        assertNotEquals("",token);
    }

    @Given("^I have a user payload$")
    public void iHaveAUserPayload() throws Throwable {
        timestamp = sdf.format(new Timestamp(new Date().getTime()));
        userPayload.setEmail("olivier.liechti_"+timestamp+"@heig-vd.ch");
        userPayload.setFirstname("Olivier");
        userPayload.setLastname("Liechti");
        userPayload.setIsAdmin(0);
        userPayload.setIsBlocked(0);
        userPayload.setPassword("oliechti");
    }

    @When("^I POST it to the /users endpoint$")
    public void iPOSTItToTheUsersEndpoint() throws Throwable {
        usersApi.getApiClient().addDefaultHeader("Authorization", "Bearer "+token);

        try {
            lastApiResponse = usersApi.createUserWithHttpInfo(userPayload);
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

    @When("^I do a GET on /users endpoint$")
    public void iDoAGETOnUsersEndpoint() throws Throwable {

        try {
            lastApiResponse = usersApi.getUsersWithHttpInfo(1,20);
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


    @And("^The list is returned in the body$")
    public void theListIsReturnedInTheBody() throws Throwable {
        assertNotEquals(lastApiResponse.getData().getClass(), List.class);
    }


}
