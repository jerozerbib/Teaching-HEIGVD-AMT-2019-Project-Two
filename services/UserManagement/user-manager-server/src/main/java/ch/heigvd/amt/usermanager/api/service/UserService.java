package ch.heigvd.amt.usermanager.api.service;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.InlineObject;
import ch.heigvd.amt.usermanager.api.model.UserInput;
import ch.heigvd.amt.usermanager.api.model.UserOutput;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;

    public UserEntity createUser(UserInput user) throws ApiException {

        if (userRepository.existsById(user.getEmail())) {
            throw new ApiException(HttpStatus.CONFLICT,"User already exists.");
        }
        UserEntity userEntity = toUserEntity(user);
        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity getUserByEmail(String email) throws ApiException {
        UserEntity userEntity = userRepository.findById(email).orElse(null);
        if (userEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND,"User not found");
        }
        return userEntity;
    }

    public List<UserOutput> getAllUsers(int numPage, int pageSize) {

        Pageable paging = PageRequest.of(numPage,pageSize);
        Page<UserOutput> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else {
            return new ArrayList<>();
        }
    }

    public void updateUser(String email, @Valid InlineObject fields) throws ApiException {

        UserEntity userEntity = getUserByEmail(email);

        String isBlocked = fields.getBlocked();
        String password = fields.getPassword();

        if (isBlocked != null){
            userEntity.setIsBlocked(Integer.parseInt(isBlocked));
        }
        if (password != null){
            userEntity.setPassword(authService.hashAndEncode(password));
        }
        userRepository.save(userEntity);
    }

    public void deleteUserByEmail(String email) throws ApiException {
        UserEntity userEntity = getUserByEmail(email);
        userRepository.delete(userEntity);
    }

    /**
     * Converts a UserInput into a UserEntity
     *
     * @param user to convert
     * @return a UserEntity
     */
    public UserEntity toUserEntity(UserInput user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setPassword(authService.hashAndEncode(user.getPassword()));
        entity.setIsAdmin(user.getIsAdmin());
        entity.setIsBlocked(user.getIsBlocked());
        return entity;
    }

    /**
     * Converts a UserEntity in a UserOutput
     *
     * @param entity to convert
     * @return a UserOutput
     */
    public UserOutput toUser(UserEntity entity) {
        UserOutput user = new UserOutput();
        user.setEmail(entity.getEmail());
        user.setIsAdmin(entity.getIsAdmin());
        user.setIsBlocked(entity.getIsBlocked());
        return user;
    }
}
