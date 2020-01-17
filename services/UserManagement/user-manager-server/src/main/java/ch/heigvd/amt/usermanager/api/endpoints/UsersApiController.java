package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.UserInput;
import ch.heigvd.amt.usermanager.api.model.UserOutput;
import ch.heigvd.amt.usermanager.api.service.UserService;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

@RestController
public class UsersApiController implements UsersApi {

    // Source : https://howtodoinjava.com/spring-boot2/spring-boot-crud-hibernate/

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    /**
     * Creates a new User from a UserInput using the request method POST
     * @param user UserInput
     * @return a new Object
     */
    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody UserInput user) throws ApiException {

        UserEntity userEntity = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(userEntity.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all the Users from the database using the request method GET
     *
     * @return a List of UserOutputs
     */
    public ResponseEntity<List<UserOutput>> getUsers() {
        List<UserOutput> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(userService.toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

    /**
     * Gets a user by his email
     * @param email of the user to get
     * @return A UserEntity
     */
    public ResponseEntity<Object> getUserById(@PathVariable String email) throws ApiException{

        UserEntity userEntity = userService.getUserByEmail(email);
        return ResponseEntity.ok(userService.toUser(userEntity));
    }

    public ResponseEntity<Object> updateUser(@ApiParam(value = "The email we need to Id the user",required=true) @PathVariable("email") String email,@ApiParam(value = "" ) @RequestHeader(value="password", required=false) String password,@ApiParam(value = "" ) @RequestHeader(value="isBlocked", required=false) String isBlocked) throws Exception {
        UserEntity userEntity = userService.getUserByEmail(email);
        if (!isBlocked.equals("")){
            userEntity.setIsBlocked(Integer.parseInt(isBlocked));
        }
        if (!password.equals("")){
            userEntity.setPassword(password);
        }
        userRepository.save(userEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a User from his email only if the deleted User is the same as the one logged in
     * @param email
     * @return The deleted User
     */
    public ResponseEntity<Object> deleteUser(@PathVariable String email) throws ApiException {

        userService.deleteUserByEmail(email);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(email).toUri();

        return ResponseEntity.created(location).build();
    }
}
