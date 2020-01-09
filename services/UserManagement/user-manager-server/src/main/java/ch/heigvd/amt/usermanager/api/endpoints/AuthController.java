package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.util.ApiResponseMessage;
import ch.heigvd.amt.usermanager.configuration.JwtToken;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.model.JwtRequest;
import ch.heigvd.amt.usermanager.model.JwtResponse;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ch.heigvd.amt.usermanager.api.model.JwtRequest user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();

        if(email == null || password == null ){
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformated request");
            throw new ApiException(HttpStatus.BAD_REQUEST.value(),"Malformated reqsadasdadsauest");
        }

        Optional<UserEntity> userOpt = userRepository.findById(email);

        if(!userOpt.isPresent()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        UserEntity userEntity = (UserEntity) userOpt.get();

        if(email.equals(user.getEmail()) && password.equals(user.getPassword())){
            String token = jwtToken.generateToken(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
        }

        return null;

    }

}
