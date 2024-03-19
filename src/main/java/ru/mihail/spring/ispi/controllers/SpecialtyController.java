package ru.mihail.spring.ispi.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.services.SpecialtyService;

import java.util.List;

@RestController
@RequestMapping("api/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping("new-spec")
    public ResponseEntity<Specialty> createSpecialty(@Valid @RequestBody Specialty specialty) {
        Specialty createdSpecialty = specialtyService.createSpecialty(specialty);
        return new ResponseEntity<>(createdSpecialty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialties() {
        List<Specialty> specialties = specialtyService.getAllSpecialties();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable Long id) {
        Specialty specialty = specialtyService.findById(id);
        if (specialty != null) {
            return new ResponseEntity<>(specialty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable Long id, @Valid @RequestBody Specialty specialtyDetails) {
        Specialty updatedSpecialty = specialtyService.updateSpecialty(id, specialtyDetails);
        if (updatedSpecialty != null) {
            return new ResponseEntity<>(updatedSpecialty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
