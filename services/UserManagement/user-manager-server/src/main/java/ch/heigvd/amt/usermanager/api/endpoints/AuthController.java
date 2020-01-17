package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.model.JwtRequest;
import ch.heigvd.amt.usermanager.api.model.JwtResponse;
import ch.heigvd.amt.usermanager.api.service.AuthService;
import ch.heigvd.amt.usermanager.api.service.UserService;
import ch.heigvd.amt.usermanager.api.util.JwtToken;
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

    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JwtRequest user) throws Exception {

        UserEntity userEntity = userService.getUserByEmail(user.getEmail());
        String token = authService.checkCreds(userEntity,user);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse().token(token));
    }

}
