package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.models.MedicalService;
import ru.mihail.spring.ispi.models.Patient;

import java.util.List;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    List<MedicalReport> findByPatient(Patient id);
}
