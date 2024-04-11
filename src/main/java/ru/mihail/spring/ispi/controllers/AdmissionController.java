package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.AdmissionDTO;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.services.Impl.AdmissionService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private Mapper mapper;

    // Создание новой записи о приеме
    @PostMapping("/create")
    public ResponseEntity<AdmissionDTO> createAdmission(@Valid @RequestBody AdmissionDTO admissionDTO) {
        Admission admission = mapper.convertToAdmissionEntity(admissionDTO);
        Admission createdAdmission = admissionService.createAdmission(admission);
        if (createdAdmission != null) {
            AdmissionDTO createdAdmissionDTO = mapper.convertToAdmissionDTO(createdAdmission);
            return new ResponseEntity<>(createdAdmissionDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Получение записи о приеме по ее идентификатору (id)
    @GetMapping("/search/{id}")
    public ResponseEntity<AdmissionDTO> getAdmissionById(@PathVariable Long id) {
        Admission admission = admissionService.getAdmissionById(id);
        if (admission != null) {
            AdmissionDTO admissionDTO = mapper.convertToAdmissionDTO(admission);
            return new ResponseEntity<>(admissionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех записей о приеме
    @GetMapping("/all")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissions() {
        List<Admission> admissions = admissionService.getAllAdmissions();
        List<AdmissionDTO> admissionDTOs = mapper.convertToAdmissionDTOList(admissions);
        return new ResponseEntity<>(admissionDTOs, HttpStatus.OK);
    }

    // Обновление записи о приеме по ее идентификатору (id)
    @PutMapping("/update/{id}")
    public ResponseEntity<AdmissionDTO> updateAdmission(@PathVariable Long id, @Valid @RequestBody AdmissionDTO admissionDTO) {
        admissionDTO.setId(id);
        Admission admission = mapper.convertToAdmissionEntity(admissionDTO);
        Admission updatedAdmission = admissionService.updateAdmission(admission);
        if (updatedAdmission != null) {
            AdmissionDTO updatedAdmissionDTO = mapper.convertToAdmissionDTO(updatedAdmission);
            return new ResponseEntity<>(updatedAdmissionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление записи о приеме по ее идентификатору (id)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmissionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
