package ch.heigvd.amt.usermanager.api.service;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import ch.heigvd.amt.usermanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity getUserByEmail(String email) throws ApiException {
        UserEntity userEntity = userRepository.findById(email).orElse(null);
        if (userEntity == null){
            throw new ApiException(HttpStatus.NOT_FOUND,"User not found");
        }
        return userEntity;
    }
}
