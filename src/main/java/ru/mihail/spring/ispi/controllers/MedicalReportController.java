package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.services.Impl.MedicalReportService;
import java.util.List;

@RestController
@RequestMapping("/medical-reports")
public class MedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    // Получение списка всех медицинских заключений
    @GetMapping("/all")
    public ResponseEntity<List<MedicalReport>> getAllMedicalReports() {
        List<MedicalReport> medicalReports = medicalReportService.getAllMedicalReports();
        return ResponseEntity.ok(medicalReports);
    }

    // Получение информации о медицинском заключении по ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalReport> getMedicalReportById(@PathVariable Long id) {
        MedicalReport medicalReport = medicalReportService.getMedicalReportById(id);
        if (medicalReport != null) {
            return ResponseEntity.ok(medicalReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Добавление нового медицинского заключения
    @PostMapping("/create")
    public ResponseEntity<MedicalReport> createMedicalReport(@RequestBody MedicalReport medicalReport) {
        MedicalReport createdMedicalReport = medicalReportService.createMedicalReport(medicalReport);
        return ResponseEntity.ok(createdMedicalReport);
    }

    // Редактирование информации о медицинском заключении
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicalReport> updateMedicalReport(@PathVariable Long id, @RequestBody MedicalReport updatedMedicalReport) {
        MedicalReport medicalReport = medicalReportService.updateMedicalReport(id, updatedMedicalReport);
        if (medicalReport != null) {
            return ResponseEntity.ok(medicalReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление медицинского заключения
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicalReport(@PathVariable Long id) {
        boolean deleted = medicalReportService.deleteMedicalReport(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

