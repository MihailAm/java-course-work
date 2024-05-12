package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.models.Patient;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    List<Admission> findByPatient(Patient id);

    // ЭТО НЕ УДАЛЯТЬ
    List<Admission> findByDoctorIdAndDate(Long doctorId, Date date);

    List<Admission> findByDoctorIdAndDateAndTime(Long doctorId, Date date, LocalTime time);
    List<Admission> findByDoctorId(Long doctorId);
    List<Admission> findByPatientId(Long patientId);
}
