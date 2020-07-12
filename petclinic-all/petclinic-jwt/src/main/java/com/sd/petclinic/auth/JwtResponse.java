package com.sd.petclinic.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String jwttoken;
    private final String username;
    private final Set<String> roles;

    public JwtResponse(String jwttoken, String username, Collection<? extends GrantedAuthority> grantedAuthority) {
        this.jwttoken = jwttoken;
        this.username = username;
        
        // Transforms  Collection<? extends GrantedAuthority> into
        // Set<String> with stream and map.
        this.roles = grantedAuthority.stream().map(a -> {
            return a.getAuthority();
        }).collect(Collectors.toSet());
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return this.username;
    }

    public Set<String> getRoles() {
        return this.roles;
    }
}