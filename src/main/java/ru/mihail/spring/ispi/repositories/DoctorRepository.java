package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mihail.spring.ispi.models.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Метод для сохранения доктора
    Doctor save(Doctor doctor);

    // Метод для получения списка всех докторов
    List<Doctor> findAll();
}
