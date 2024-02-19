package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.MedicalReport;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    /*
    СОЗДАТЬ РЕПОЗИТОРИЙ
    @Autowired
    private MedicalReportRepository medicalReportRepository;
    */

    public List<MedicalReport> getAllMedicalReports() {
        //return medicalReportRepository.findAll();
        return null;  //tmp
    }

    public MedicalReport getMedicalReportById(Long id) {
        //Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(id);
        //return optionalMedicalReport.orElse(null);
        return null;  //tmp
    }

    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        // Логика проверок перед сохранением, если необходимо
        //return medicalReportRepository.save(medicalReport);
        return null;  //tmp
    }

    public MedicalReport updateMedicalReport(Long id, MedicalReport updatedMedicalReport) {
        /*
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
         */
        return null;  //tmp
    }

    public boolean deleteMedicalReport(Long id) {
        /*
        Optional<MedicalReport> optionalMedicalReport = medicalReportRepository.findById(id);
        if (optionalMedicalReport.isPresent()) {
            medicalReportRepository.deleteById(id);
            return true;
        } else {
            return false; // Запись не найдена
        }
        */

        return false; //tmp
    }
}

