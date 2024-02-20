package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.models.MedicalService;
import ru.mihail.spring.ispi.services.MedicalServiceService;

import java.util.List;

@RestController
@RequestMapping("/medical-services")
public class MedicalServiceController {

    @Autowired
    private MedicalServiceService medicalServiceService; // Предположим, что у вас есть сервис для работы с медицинскими услугами

    // Получение списка всех медицинских услуг
    @GetMapping("/all")
    public ResponseEntity<List<MedicalService>> getAllMedicalServices() {
        List<MedicalService> medicalServices = medicalServiceService.getAllMedicalServices();
        return ResponseEntity.ok(medicalServices);
    }

    // Поиск медицинской услуги по id
    @GetMapping("/{id}")
    public ResponseEntity<MedicalService> getMedicalServiceById(@PathVariable Long id) {
        MedicalService medicalService = medicalServiceService.getMedicalServiceById(id);
        if (medicalService != null) {
            return ResponseEntity.ok(medicalService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Добавление новой услуги
    @PostMapping("/create")
    public ResponseEntity<MedicalService> createMedicalService(@RequestBody MedicalService medicalService) {
        MedicalService createdMedicalService = medicalServiceService.createMedicalService(medicalService);
        return ResponseEntity.ok(createdMedicalService);
    }

    // Изменение медицинской услуги по id
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicalService> updateMedicalServiceById(@PathVariable Long id,
                                                                   @RequestBody MedicalService updatedMedicalService) {
        MedicalService medicalService = medicalServiceService.updateMedicalServiceById(id, updatedMedicalService);
        if (medicalService != null) {
            return ResponseEntity.ok(medicalService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление медицинской услуги по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicalServiceById(@PathVariable Long id) {
        boolean deleted = medicalServiceService.deleteMedicalServiceById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

