package ch.heigvd.amt.usermanager.api.spec.helpers;

import ch.heigvd.amt.usermanager.ApiException;
import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.AuthenticateApi;
import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.dto.*;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private UsersApi usersApi = new UsersApi();
    private AuthenticateApi authApi = new AuthenticateApi();

    private JwtRequest loginRequest;
    private ApiResponse<JwtResponse> loginResponse;
    private ApiResponse<Object> postResponse;
    private ApiResponse<List<UserOutput>> getResponse;



    private UserInput userPayload;
    private String token;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.usermanager.server.url");
        usersApi.getApiClient().setBasePath(url);
        authApi.getApiClient().setBasePath(url);
    }

    public UsersApi getUsersApi() {
        return usersApi;
    }

    public AuthenticateApi getAuthApi() {
        return authApi;
    }

    public void setUsersApi(UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    public void setAuthApi(AuthenticateApi authApi) {
        this.authApi = authApi;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public JwtRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(JwtRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public ApiResponse<JwtResponse> getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(ApiResponse<JwtResponse> loginResponse) {
        this.loginResponse = loginResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInput getUserPayload() {
        return userPayload;
    }

    public void setUserPayload(UserInput userPayload) {
        this.userPayload = userPayload;
    }

    public ApiResponse<Object> getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(ApiResponse<Object> postResponse) {
        this.postResponse = postResponse;
    }

    public ApiResponse<List<UserOutput>> getGetResponse() {
        return getResponse;
    }

    public void setGetResponse(ApiResponse<List<UserOutput>> getResponse) {
        this.getResponse = getResponse;
    }
}