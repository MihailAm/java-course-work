package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.models.Patient;

import java.util.List;

public interface MedicalReportServiceInterface {
    MedicalReport createMedicalReport(MedicalReport medicalReport);
    void deleteMedicalReport(Long id);
    MedicalReport getMedicalReportById(Long id);
    MedicalReport updateMedicalReport(MedicalReport medicalReport);
    List<MedicalReport> getAllMedicalReports();

    List<MedicalReport> findMedicalReportByPatient(Patient id);
}
