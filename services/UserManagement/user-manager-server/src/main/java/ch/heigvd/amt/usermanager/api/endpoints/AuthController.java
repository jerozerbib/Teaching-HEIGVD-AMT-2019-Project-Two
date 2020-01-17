package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.JwtRequest;
import ch.heigvd.amt.usermanager.api.model.JwtResponse;
import ch.heigvd.amt.usermanager.api.util.JwtToken;

import ch.heigvd.amt.usermanager.api.util.PasswordHash;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private JwtToken jwtToken;

    @Autowired
    private UserRepository userRepository;

    private PasswordHash passwordHash = new PasswordHash(5);
    
    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JwtRequest user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();


        UserEntity userEntity = userRepository.findById(email).orElse(null);

        if(userEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
        }

        if(email.equals(userEntity.getEmail()) && passwordHash.authenticate(password, userEntity.getPassword())){
            String token = jwtToken.generateToken(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse().token(token));
        } else {
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Bad credentials");
        }
    }

}
