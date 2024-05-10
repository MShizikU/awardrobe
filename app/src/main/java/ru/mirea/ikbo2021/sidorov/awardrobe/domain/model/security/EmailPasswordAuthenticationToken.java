package ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {
    private final String email;
    private final String password;

    public EmailPasswordAuthenticationToken(String email, String password) {
        super(null);
        this.email = email;
        this.password = password;
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
