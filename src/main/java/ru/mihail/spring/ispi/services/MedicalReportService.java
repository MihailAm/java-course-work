package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    @Autowired
    private MedicalReportRepository medicalReportRepository; // Предположим, что у вас есть репозиторий для работы с медицинскими заключениями

    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    public MedicalReport getMedicalReportById(Long id) {
        Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(id);
        return optionalMedicalReport.orElse(null);
    }

    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        // Логика проверок перед сохранением, если необходимо
        return medicalReportRepository.save(medicalReport);
    }

    public MedicalReport updateMedicalReport(Long id, MedicalReport updatedMedicalReport) {
        Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(id);
        if (optionalMedicalReport.isPresent()) {
            MedicalReport existingMedicalReport = optionalMedicalReport.get();

            // Обновление полей медицинского заключения
            existingMedicalReport.setDiagnosis(updatedMedicalReport.getDiagnosis());
            existingMedicalReport.setPrescription(updatedMedicalReport.getPrescription());
            // Обновление других полей по необходимости

            return medicalReportRepository.save(existingMedicalReport);
        } else {
            return null; // Запись не найдена
        }
    }

    public boolean deleteMedicalReport(Long id) {
        Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(id);
        if (optionalMedicalReport.isPresent()) {
            medicalReportRepository.deleteById(id);
            return true;
        } else {
            return false; // Запись не найдена
        }
    }
}

