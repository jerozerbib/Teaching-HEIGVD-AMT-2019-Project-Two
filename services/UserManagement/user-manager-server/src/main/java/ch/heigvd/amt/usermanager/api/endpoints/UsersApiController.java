package ch.heigvd.amt.usermanager.api.endpoints;

import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.UserInput;
import ch.heigvd.amt.usermanager.api.model.UserOutput;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

@RestController
@RequestMapping("/users")
public class UsersApiController implements UsersApi {

    // Source : https://howtodoinjava.com/spring-boot2/spring-boot-crud-hibernate/

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new User from a UserInput using the request method POST
     * @param user UserInput
     * @return a new Object
     */
    @PostMapping
    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody UserInput user) {
        UserEntity newUserEntity = toUserEntity(user);

        //TODO: Ajouter le check de ADMIN ou pas avec le Token JWT

//        try {
//            if (JWT.isAdmin != user.getIsAdmin()){
//                throw new ApiException(403, "You cannot perform this action as a non-admin user");
//            }
//        } catch (ApiException e){
//            e.printStackTrace();
//        }

        try {
            if (userRepository.existsById(newUserEntity.getEmail())) {
                throw new ApiException(409, "User already exist. Impossible to create it.");
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }

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
    @GetMapping("/{email}")
    public UserEntity getUserById(@PathVariable String email) throws Exception {
        Optional<UserEntity> userEntity = userRepository.findById(email);
        if (userEntity.isPresent()){
            return userEntity.get();
        } else {
            throw new ApiException(404, "No employee record exist for given id");
        }
    }



    /**
     * Gets all the Users from the database using the request method GET
     *
     * @return a List of UserOutputs
     */
    @GetMapping
    public ResponseEntity<List<UserOutput>> getUsers() {
        //TODO : Rajouter le check ADMIN ou non avec le token
        List<UserOutput> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

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

    @PutMapping("/{email")
    public ResponseEntity<Object> updateUser(@RequestBody UserInput user, @PathVariable String email) {

        //TODO : Controle JWT sur identite et retour de user qui modifie !
        //TODO : Si le tokenn JWT n'est pas celui de l'utilisateur modifie alors on yield une erreur
        //TODO : Si user est admin, il peut modifier le isBlocked. Sinon erreur
        //TODO : Pseudo code fait en bas mais manque le JWT
//        try {
//            if (!user.getIsAdmin() != JWT.getIsAdmin) {
//                throw new ApiException(400, "You cannot edit the admin parameter")
//            }
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }

//        if (user.getIsBlocked() != JWT.getIsBlocked) {
//            try {
//                if (!user.getIsAdmin()){
//                    throw new ApiException(400, "You do not have the rights to perform this action");
//                }
//            } catch (ApiException e){
//                e.printStackTrace();
//            }
//        }

        Optional<UserEntity> userEntity = userRepository.findById(email);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(email).toUri();
        try {
            if (!userRepository.existsById(email)){
                throw new ApiException(404, "User does not exist. Impossible to update it");
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }

        userRepository.save(userEntity.get());
        return ResponseEntity.created(location).build();
    }

    /**
     * Deletes a User from his email only if the deleted User is the same as the one logged in
     * @param email
     * @return The deleted User
     * @throws Exception
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteUser(String email) throws Exception {

        // TODO : Check JWT et retour User qui modifie

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(email).toUri();

        if (!userRepository.existsById(email)){
            throw new Error("User does not exist. Impossible to delete it");
        } else {
            userRepository.delete(getUserById(email));
            return ResponseEntity.created(location).build();
        }
    }
}
