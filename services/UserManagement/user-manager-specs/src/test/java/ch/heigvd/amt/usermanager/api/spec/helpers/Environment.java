package ch.heigvd.amt.usermanager.api.spec.helpers;

import ch.heigvd.amt.usermanager.api.UsersApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private UsersApi api = new UsersApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.usermanager.server.url");
        api.getApiClient().setBasePath(url);

    }

    public UsersApi getApi() {
        return api;
    }


}