//package ch.heigvd.amt.usermanager.api.spec.steps;
//
//import ch.heigvd.amt.usermanager.ApiException;
//import ch.heigvd.amt.usermanager.ApiResponse;
//import ch.heigvd.amt.usermanager.api.AuthenticateApi;
//import ch.heigvd.amt.usermanager.api.UsersApi;
//import ch.heigvd.amt.usermanager.api.dto.JwtRequest;
//import ch.heigvd.amt.usermanager.api.dto.JwtResponse;
//import ch.heigvd.amt.usermanager.api.dto.UserInput;
//import ch.heigvd.amt.usermanager.api.dto.UserOutput;
//import ch.heigvd.amt.usermanager.api.spec.helpers.Environment;
//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//
//import java.text.SimpleDateFormat;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class UserSteps {
//
//    private Environment environment;
//    private AuthenticateApi authApi;
//    private UsersApi usersApi;
//
//    private ApiResponse lastApiResponse;
//    private ApiException lastApiException;
//    private boolean lastApiCallThrewException;
//    private int lastStatusCode;
//
//    private JwtRequest loginRequest;
//    private String token;
//    private UserInput userPayload;
//
//
//    public UserSteps(Environment environment) {
//        this.environment = environment;
//        this.authApi = environment.getAuthApi();
//        this.usersApi = environment.getUsersApi();
//        this.lastApiResponse = environment.getLastApiResponse();
//        this.token = environment.getToken();
//        this.userPayload = new UserInput();
//
//    }
//
//    @Given("^I have an user login payload$")
//    public void iHaveAnUserLoginPayload() throws Throwable {
//        loginRequest = new JwtRequest();
//        loginRequest.setEmail("adrien.allemand@heig-vd.ch");
//        loginRequest.setPassword("aallemand");
//    }
//
//    @When("^I POST the user login payload to the /authenticate endpoint$")
//    public void iPOSTTheUserLoginPayloadToTheAuthenticateEndpoint() throws Throwable {
//        try {
//            lastApiResponse = authApi.createAuthenticationTokenWithHttpInfo(loginRequest);
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
//    @Then("^I receive (\\d+) status code$")
//    public void iReceiveStatusCode(int arg0) throws Throwable {
//        assertEquals(arg0, lastStatusCode);
//    }
//
//    @And("^User token is returned in the body$")
//    public void userTokenIsReturnedInTheBody() throws Throwable {
//        token = ((JwtResponse)lastApiResponse.getData()).getToken();
//        assertNotNull(token);
//        assertNotEquals("",token);
//    }
//
//    @Given("^I have my email$")
//    public void iHaveMyEmail() throws Throwable {
//        assertEquals("adrien.allemand@heig-vd.ch",loginRequest.getEmail());
//    }
//
//    @When("^I do a GET on /users/email endpoint$")
//    public void iDoAGETOnUsersEmailEndpoint() throws Throwable {
//        try {
//            lastApiResponse = usersApi.getUserByIdWithHttpInfo(loginRequest.getEmail());
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
//    @And("^My informations are returned in the body$")
//    public void myInformationsAreReturnedInTheBody() throws Throwable {
//        UserOutput user = (UserOutput) (lastApiResponse.getData());
//        assertEquals(user.getEmail(), "adrien.allemand@heig-vd.ch");
//        assertEquals(user.getFirstname(), "Adrien");
//        assertEquals(user.getLastname(), "Allemand");
//        assertEquals(user.getIsAdmin(), (Integer) 0);
//        assertEquals(user.getIsBlocked(), (Integer) 0);
//    }
//}
