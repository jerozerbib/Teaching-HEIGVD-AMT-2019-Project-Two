package ch.heigvd.amt.usermanager.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")
public class ApiException extends Exception{
    private HttpStatus code;

    /**
     * Builds an ApiException
     * @param code
     * @param msg
     */
    public ApiException (HttpStatus code, String msg) {
        super(msg);
        this.code = code;
    }
}
