package ru.mihail.spring.ispi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.DoctorDTO;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.services.Impl.DoctorService;
import ru.mihail.spring.ispi.services.Impl.SpecialtyService;
import ru.mihail.spring.ispi.services.Impl.UsersService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private Mapper Mapper;


    // Получение врача по его идентификатору (id)
    @GetMapping("/search/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            DoctorDTO doctorDTO = Mapper.convertToDoctorDTO(doctor);
            return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех врачей
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDTO> doctorDTOs = Mapper.convertToDoctorDTOList(doctors);
        return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
    }

    //    Обновление врача по id
    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        doctorDTO.setId(id);
        Doctor doctor = Mapper.convertToDoctorEntity(doctorDTO);
        Doctor updatedDoctor = doctorService.updateDoctor(doctor);
        if (updatedDoctor != null) {
            DoctorDTO updatedDoctorDTO = Mapper.convertToDoctorDTO(updatedDoctor);
            return new ResponseEntity<>(updatedDoctorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Удаление врача по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Получение врачей по специальности
    @GetMapping("/searchBySpec")
    public ResponseEntity<List<DoctorDTO>> searchDoctorsBySpecialty(@RequestParam String specialtyName) {
        List<Doctor> doctors = doctorService.searchDoctorsBySpecialty(specialtyName);
        List<DoctorDTO> doctorDTOs = Mapper.convertToDoctorDTOList(doctors);
        return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
    }

}
