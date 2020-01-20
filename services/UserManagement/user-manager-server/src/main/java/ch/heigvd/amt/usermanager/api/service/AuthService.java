package ch.heigvd.amt.usermanager.api.service;

import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.model.JwtRequest;
import ch.heigvd.amt.usermanager.api.util.JwtToken;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Base64;

@Service
public class AuthService {


    @Autowired
    private JwtToken jwtToken;

    private SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();


    /**
     * Check credentials for a User
     * @param userDB
     * @param userRequest
     * @return
     * @throws ApiException
     */
    public String checkCreds(UserEntity userDB, @Valid JwtRequest userRequest) throws ApiException {

        if(userDB.getIsBlocked() == 1){
            throw new ApiException(HttpStatus.FORBIDDEN, "User has been blocked");
        }
        else if (userRequest.getEmail().equals(userDB.getEmail()) && hashAndEncode(userRequest.getPassword()).equals(userDB.getPassword())) {
                return jwtToken.generateToken(userDB);
        }else {
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
        }
    }

    /**
     * Hash and encodes a password
     * @param password
     * @return
     */
    public String hashAndEncode(String password){
        byte[] digest = digestSHA3.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(digest);
    }

}
