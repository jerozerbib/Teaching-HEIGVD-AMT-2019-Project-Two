package ch.heigvd.amt.usermanager.api.exceptions;

import org.springframework.http.HttpStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

public class NotFoundException extends ApiException {
    private HttpStatus code;
    public NotFoundException (HttpStatus code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
