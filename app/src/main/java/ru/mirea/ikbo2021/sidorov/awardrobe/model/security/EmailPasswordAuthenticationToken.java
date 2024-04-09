package ru.mirea.ikbo2021.sidorov.awardrobe.model.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {
    private final String email;
    private final String password;

    public EmailPasswordAuthenticationToken(String email, String password) {
        super(null);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
