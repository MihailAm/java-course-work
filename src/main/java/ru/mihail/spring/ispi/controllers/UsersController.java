package ru.mihail.spring.ispi.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.DoctorAuthDTO;
import ru.mihail.spring.ispi.Dto.PatientAuthDTO;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.services.DoctorService;
import ru.mihail.spring.ispi.services.SpecialtyService;
import ru.mihail.spring.ispi.services.UsersService;
import ru.mihail.spring.ispi.services.PatientService;

@RestController
@RequestMapping("api/auth")
public class UsersController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ru.mihail.spring.ispi.Dto.Mapper.Mapper Mapper;

    // Медот регистрации доктора
    @Transactional
    @PostMapping("/register-doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorAuthDTO doctorRequest) {
        Users user = Mapper.DoctorToUsersEntity(doctorRequest);
        usersService.saveDoctor(user);
        Doctor doctor = Mapper.DoctorToDoctorEntity(doctorRequest, user);
        doctorService.save(doctor);
        return new ResponseEntity<>("Doctor registered successfully", HttpStatus.CREATED);
    }

    // Медот регистрации пациента
    @Transactional
    @PostMapping("/register-patient")
    public ResponseEntity<String> registerPatient(@RequestBody @Valid PatientAuthDTO patientRequest) {
        Users user = Mapper.PatientToUsersEntity(patientRequest);
        usersService.savePatient(user);
        Patient patient = Mapper.PatientToPatientEntity(patientRequest, user);
        patientService.save(patient);

        return new ResponseEntity<>("Patient registered successfully", HttpStatus.CREATED);


    }

}
