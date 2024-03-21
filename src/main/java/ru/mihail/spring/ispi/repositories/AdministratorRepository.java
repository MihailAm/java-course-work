package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Administrator;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Users;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByUser(Users user);
}
