package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    Users findByEmailAndPassword(String email, String password);

}
