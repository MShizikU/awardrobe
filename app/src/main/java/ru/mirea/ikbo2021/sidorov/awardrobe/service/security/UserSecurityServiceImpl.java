package ru.mirea.ikbo2021.sidorov.awardrobe.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces.UserSecurityService;

@Service
@RequiredArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {
    private final UserRepository repository;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> repository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
