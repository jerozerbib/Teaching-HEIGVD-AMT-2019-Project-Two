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


    public String checkCreds(UserEntity userEntity, @Valid JwtRequest user) throws ApiException {

        if (user.getEmail().equals(userEntity.getEmail()) && hashAndEncode(user.getPassword()).equals(userEntity.getPassword())) {
                return jwtToken.generateToken(userEntity);
        }else {
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
        }
    }

    public String hashAndEncode(String password){
        byte[] digest = digestSHA3.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(digest);
    }

}
