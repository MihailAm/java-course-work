package ru.mihail.spring.ispi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.repositories.UserRepository;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = repository.findByEmail(email);
        return users.map(UsersDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("Not found"));
    }
}
