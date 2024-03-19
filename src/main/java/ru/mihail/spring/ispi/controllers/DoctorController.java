package ru.mihail.spring.ispi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.DoctorAuthDTO;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.services.DoctorService;
import ru.mihail.spring.ispi.services.SpecialtyService;
import ru.mihail.spring.ispi.services.UsersService;
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

//    @Transactional
//    @PostMapping("/register-doctor")
//    public ResponseEntity<String> registerDoctor(@RequestBody DoctorAuthDTO doctorRequest) {
//        // СОЗДАНИЯ ПОЛЬЗОВАТЕЛЯ
//        Users user = new Users();
//
//        user.setEmail(doctorRequest.getEmail());
//        user.setPassword(doctorRequest.getPassword());
//        usersService.saveDoctor(user);
//
//        // СОЗДАНИЕ ДОКТОРА
//        Doctor doctor = new Doctor();
//        doctor.setUser(user);
//        doctor.setFirstName(doctorRequest.getFirstName());
//        doctor.setLastName(doctorRequest.getLastName());
//        doctor.setPosition(doctorRequest.getPosition());
//
//
//        Specialty specialty = specialtyService.findById(doctorRequest.getSpecialtyId());
//        doctor.setSpecialty(specialty);
//
//        doctorService.save(doctor);
//
//        return new ResponseEntity<>("Doctor registered successfully", HttpStatus.CREATED);
//    }



    // Получение врача по его идентификатору (id)
    @GetMapping("/search/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех врачей
    @GetMapping("all-doctor")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    //    Обновление врача по id
    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @Valid @RequestBody Doctor doctor) {
        doctor.setId(id);
        Doctor updatedDoctor = doctorService.updateDoctor(doctor);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    //    Удаление врача по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Получение врачей по специальности
    @GetMapping("/searchBySpec")
    public List<Doctor> searchDoctorsBySpecialty(@RequestParam String specialtyName) {
        return doctorService.searchDoctorsBySpecialty(specialtyName);
    }

}
