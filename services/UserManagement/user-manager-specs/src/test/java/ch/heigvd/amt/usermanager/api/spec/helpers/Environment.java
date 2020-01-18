package ch.heigvd.amt.usermanager.api.spec.helpers;

import ch.heigvd.amt.usermanager.ApiException;
import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.AuthenticateApi;
import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.dto.JwtRequest;
import ch.heigvd.amt.usermanager.api.dto.JwtResponse;
import ch.heigvd.amt.usermanager.api.dto.User;
import ch.heigvd.amt.usermanager.api.dto.UserInput;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private UsersApi usersApi = new UsersApi();
    private AuthenticateApi authApi = new AuthenticateApi();

    private UserInput userAdmin = new UserInput();

    private OkHttpClient okHttpClient;

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

        userAdmin.setEmail("adam.zouari@heig-vd.ch");
        userAdmin.setPassword("azouari");

    }

    public UsersApi getUsersApi() {
        return usersApi;
    }

    public AuthenticateApi getAuthApi() {
        return authApi;
    }

    public UserInput getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(UserInput userAdmin) {
        this.userAdmin = userAdmin;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
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
}