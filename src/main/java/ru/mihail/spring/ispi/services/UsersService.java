package ru.mihail.spring.ispi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UsersService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    //Метод для создания человека
    @Transactional
    public void save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole("ROLE_PATIENT");
        userRepository.save(users);
    }
}
