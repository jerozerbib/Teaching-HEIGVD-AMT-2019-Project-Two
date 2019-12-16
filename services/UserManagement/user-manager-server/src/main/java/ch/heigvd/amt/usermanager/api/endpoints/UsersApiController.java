package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.model.UserInput;
import ch.heigvd.amt.usermanager.api.model.UserOutput;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

@Controller
public class UsersApiController implements UsersApi {

    // Source : https://howtodoinjava.com/spring-boot2/spring-boot-crud-hibernate/

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new User from a UserInput using the request method POST
     *
     * @param user UserInput
     * @return a new Object
     */
    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody UserInput user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(newUserEntity.getEmail()).toUri();

        userRepository.save(newUserEntity);

        return ResponseEntity.created(location).build();
    }

    /**
     * Converts a UserInput into a UserEntity
     *
     * @param user to convert
     * @return a UserEntity
     */
    private UserEntity toUserEntity(@Valid UserInput user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setSalt(user.getSalt());
        entity.setAdmin(user.getIsAdmin());
        entity.setBlocked(user.getIsBlocked());
        return entity;
    }

    /**
     * Gets a user by his email
     * @param email of the user to get
     * @return A UserEntity
     * @throws Exception
     */
    public UserEntity getUserById(String email) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(email);
        if (userEntity.isPresent()){
            return userEntity.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }


    /**
     * Gets all the Users from the database using the request method GET
     *
     * @return a List of UserOutputs
     */
    public ResponseEntity<List<UserOutput>> getUsers() {
        List<UserOutput> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    Page<UserOutput> users(Pageable pageable) {
//        return userRepository.findAll(pageable);
//
//    }

    /**
     * Converts a UserEntity in a UserOutput
     *
     * @param entity to convert
     * @return a UserOutput
     */
    private UserOutput toUser(UserEntity entity) {
        UserOutput user = new UserOutput();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setIsAdmin(entity.isAdmin());
        user.setIsBlocked(entity.isBlocked());
        return user;
    }
}
