package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Users;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Метод для сохранения доктора
    Doctor save(Doctor doctor);

    // Метод для получения списка всех докторов
    List<Doctor> findAll();

    Doctor findByUser(Users user);
}
