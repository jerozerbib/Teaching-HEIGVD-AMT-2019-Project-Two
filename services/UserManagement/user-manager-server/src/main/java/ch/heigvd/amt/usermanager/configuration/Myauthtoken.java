package ch.heigvd.amt.usermanager.configuration;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class Myauthtoken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    public Myauthtoken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public Myauthtoken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}