package ch.heigvd.amt.usermanager.api.spec.helpers;

import ch.heigvd.amt.usermanager.ApiException;
import ch.heigvd.amt.usermanager.ApiResponse;
import ch.heigvd.amt.usermanager.api.ProductApi;
import ch.heigvd.amt.usermanager.api.dto.*;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private ProductApi productApi = new ProductApi();

    private ApiResponse<Object> postResponse;

    private Product productPayload;
    private String token;

    private OkHttpClient client = new OkHttpClient();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.chillout.server.url");
        productApi.getApiClient().setBasePath(url);
    }

    public ProductApi getProductApi() {
        return productApi;
    }

    public void setProductApi(ProductApi productApi) {
        this.productApi = productApi;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApiResponse<Object> getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(ApiResponse<Object> postResponse) {
        this.postResponse = postResponse;
    }

    public OkHttpClient getHttpClient() { return client; }
}