package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.services.Impl.AdmissionService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    // Получение списка всех приемов
    @GetMapping("/all")
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        List<Admission> admissions = admissionService.getAllAdmissions();
        return ResponseEntity.ok(admissions);
    }

    // Создать прием
    @PostMapping("/create")
    public ResponseEntity<Admission> createAdmission(@RequestBody Admission admission) {
        Admission createdAdmission = admissionService.createAdmission(admission);
        return ResponseEntity.ok(createdAdmission);
    }

    // Поиск приема по id
    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        Admission admission = admissionService.getAdmissionById(id);
        if (admission != null) {
            return ResponseEntity.ok(admission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить прием по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmissionById(@PathVariable Long id) {
        boolean deleted = admissionService.deleteAdmissionById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Поиск приемов по дате
    @GetMapping("/byDate")
    public ResponseEntity<List<Admission>> getAdmissionsByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Admission> admissions = admissionService.getAdmissionsByDate(date);
        return ResponseEntity.ok(admissions);
    }
}

