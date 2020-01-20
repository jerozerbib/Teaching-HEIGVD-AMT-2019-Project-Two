package ch.heigvd.amt.chillout.api.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;

    /**
     * Constructor of the Api Error
     * @param status
     * @param message
     */
    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


}