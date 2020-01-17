package ch.heigvd.amt.usermanager.api.util;


import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtToken implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    // TODO Check validity
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;


    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public int getIsAdminFromToken(String token) {
        return getAllClaimsFromToken(token).get("isAdmin", Integer.class);
    }

    public int getIsBlockedFromToken(String token) {
        return getAllClaimsFromToken(token).get("isBlocked", Integer.class);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("isAdmin", user.getIsAdmin());
        claims.put("isBlocked",user.getIsBlocked());
        return doGenerateToken(claims, user.getEmail());
    }


    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public void verify(String token) throws ApiException {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();
        if(!isTokenExpired(token)){
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Your token has expired");
        }
    }

    public String getToken(HttpServletRequest req){
        return req.getHeader("Authorization").split(" ")[1];
    }
}
