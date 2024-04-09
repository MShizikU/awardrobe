package ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService {
    UserDetailsService userDetailsService();
}
