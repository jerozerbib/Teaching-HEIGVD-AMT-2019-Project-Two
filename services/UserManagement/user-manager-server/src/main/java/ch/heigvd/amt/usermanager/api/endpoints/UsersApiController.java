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
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

@RestController
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

        if (userRepository.existsById(newUserEntity.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
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
     */
    public ResponseEntity<Object> getUserById(@PathVariable String email) {
        Optional<UserEntity> userEntity = userRepository.findById(email);
        if (!userEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist. Impossible to update it");
        }

        return ResponseEntity.ok(toUser(userEntity.get()));
    }

    /**
     * Gets all the Users from the database using the request method GET
     *
     * @return a List of UserOutputs
     */
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

    public ResponseEntity<Object> updateUser(@RequestHeader String password, @PathVariable String email) {

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

//        try {
//            if (email != user.getEmail()) {
//                throw new ApiException(403, "You are not authorized to edit this ressource");
//            }
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }

        if (!userEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist. Impossible to update it");
        }

        userEntity.get().setPassword(password);

        userRepository.save(userEntity.get());
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a User from his email only if the deleted User is the same as the one logged in
     * @param email
     * @return The deleted User
     */
    public ResponseEntity<Object> deleteUser(@PathVariable String email) {

        // TODO : Check JWT et retour User qui modifie

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(email).toUri();

        Optional<UserEntity> userEntity = userRepository.findById(email);

        if (!userEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist. Impossible to update it");
        } else {
            userRepository.delete(userEntity.get());
        }

        return ResponseEntity.created(location).build();
    }
}
