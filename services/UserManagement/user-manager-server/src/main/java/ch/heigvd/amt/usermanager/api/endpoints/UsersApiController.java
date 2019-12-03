package ch.heigvd.amt.usermanager.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import ch.heigvd.amt.usermanager.api.UsersApi;
import ch.heigvd.amt.usermanager.api.model.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-02T18:00:35.658Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        String email = newUserEntity.getEmail();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(newUserEntity.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }

    private UserEntity toUserEntity(@Valid User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword()); // TODO Check si le password doit etre la..
        return entity;
    }

    public ResponseEntity<List<User>> getUsers() {List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        //TODO handle pagination
        /*
        User staticUser = new User();
        staticUser.setColour("red");
        staticUser.setKind("banana");
        staticUser.setSize("medium");
        List<User> Users = new ArrayList<>();
        Users.add(staticUser);
        */
        return ResponseEntity.ok(users);
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword()); // TODO Check si le password doit etre la..
        return user;
    }

}
