package ru.mihail.spring.ispi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.repositories.MedicalReportRepository;
import ru.mihail.spring.ispi.services.Interfaces.MedicalReportServiceInterface;

import java.util.List;

@Service
public class MedicalReportService implements MedicalReportServiceInterface {

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Override
    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    @Override
    public void deleteMedicalReport(Long id) {
        medicalReportRepository.deleteById(id);
    }

    @Override
    public MedicalReport getMedicalReportById(Long id) {
        return medicalReportRepository.findById(id).orElse(null);
    }

    @Override
    public MedicalReport updateMedicalReport(MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    @Override
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    @Override
    public List<MedicalReport> findMedicalReportByPatient(Patient patient) {
        return medicalReportRepository.findByPatient(patient);
    }
}
