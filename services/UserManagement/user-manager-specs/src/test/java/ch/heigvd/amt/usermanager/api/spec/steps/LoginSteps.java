package ch.heigvd.amt.usermanager.api.spec.steps;

import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.AuthenticateApi;
import ch.heigvd.amt.usermanager.api.dto.JwtRequest;
import ch.heigvd.amt.usermanager.api.dto.JwtResponse;
import ch.heigvd.amt.usermanager.api.spec.helpers.Environment;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class LoginSteps {

    private Environment environment;
    private AuthenticateApi authApi;
    private JwtRequest loginRequest;
    private ApiResponse<JwtResponse> loginResponse;
    private String token;

    public LoginSteps(Environment environment){
        this.environment = environment;
        this.authApi= environment.getAuthApi();
    }

    @Given("^there is a Users server$")
    public void thereIsAUsersServer() throws Throwable {
        assertNotNull(authApi);
    }

    @Given("^I have a login payload$")
    public void iHaveALoginPayload() throws Throwable {
        loginRequest = new JwtRequest();
        loginRequest.setEmail("adam.zouari@heig-vd.ch");
        loginRequest.setPassword("azouari");
    }

    @When("^I POST it to the /authenticate endpoint$")
    public void iPOSTItToTheAuthenticateEndpoint() throws Throwable {
        loginResponse = authApi.createAuthenticationTokenWithHttpInfo(loginRequest);
    }


    @And("^Token is returned in the body$")
    public void tokenIsReturnedInTheBody() throws Throwable {
        token = loginResponse.getData().getToken();
        assertNotNull(token);
        assertNotEquals("",token);

    }

    @Then("^I receive  (\\d+) status code$")
    public void iReceiveStatusCode(int arg0) throws Throwable {
        assertEquals(arg0, loginResponse.getStatusCode());
    }

}
