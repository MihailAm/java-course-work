package ru.mihail.spring.ispi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.models.Users;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUser(Users user);
}
