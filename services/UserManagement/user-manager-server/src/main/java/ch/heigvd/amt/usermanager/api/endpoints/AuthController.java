package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.JwtRequest;
import ch.heigvd.amt.usermanager.api.model.JwtResponse;
import ch.heigvd.amt.usermanager.api.service.AuthService;
import ch.heigvd.amt.usermanager.api.service.UserService;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * Create a JWT token for the authentication
     * @param userRequest
     * @return ResponseEntity
     * @throws ApiException
     */
    @PostMapping
    public ResponseEntity<JwtResponse> createAuthenticationToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JwtRequest userRequest) throws ApiException {

        UserEntity userDB = userService.getUserByEmail(userRequest.getEmail());
        String token = authService.checkCreds(userDB,userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponse().token(token));
    }

}
