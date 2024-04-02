package ru.mihail.spring.ispi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mihail.spring.ispi.Dto.PatientDTO;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.services.Impl.PatientService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private Mapper mapper;

    // Получение пациента по его идентификатору (id)
    @GetMapping("/search/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            PatientDTO patientDTO = mapper.convertToPatientDTO(patient);
            return new ResponseEntity<>(patientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех пациентов
    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientDTO> patientDTOs = mapper.convertToPatientDTOList(patients);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }


    //    Обновление пациента по id
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO) {
        patientDTO.setId(id);
        Patient patient = mapper.convertToPatientEntity(patientDTO);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        if (updatedPatient != null) {
            PatientDTO updatedPatientDTO = mapper.convertToPatientDTO(updatedPatient);
            return new ResponseEntity<>(updatedPatientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Удаление пациента по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
