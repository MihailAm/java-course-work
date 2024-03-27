package ru.mihail.spring.ispi.services.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.repositories.UserRepository;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {
    private final UserRepository userRepository;
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Users Get(long id) {
        return userRepository.findById(id).orElseThrow();
    }


    public Users Get(String email) {
        return userRepository.findByEmail(email);
    }


    public List<Users> GetAll() {
        return userRepository.findAll();
    }

    public Boolean Delete(long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = Get(email);
        if (user == null){
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден",email));
        };
        return user;
    }
}
