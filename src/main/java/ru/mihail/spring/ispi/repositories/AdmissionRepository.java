package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.models.Schedule;

import java.util.Date;
import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    // ЭТО НЕ УДАЛЯТЬ
    List<Admission> findByDoctorIdAndDate(Long doctorId, Date date);
}