package ru.mihail.spring.ispi.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.AdministratorAuthDTO;
import ru.mihail.spring.ispi.Dto.DoctorAuthDTO;
import ru.mihail.spring.ispi.Dto.LoginDTO;
import ru.mihail.spring.ispi.Dto.PatientAuthDTO;
import ru.mihail.spring.ispi.models.Administrator;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.services.*;
import ru.mihail.spring.ispi.services.Impl.*;

@RestController
@RequestMapping("api/auth")
public class UsersController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PatientService patientService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ru.mihail.spring.ispi.Dto.Mapper.Mapper Mapper;

    // Медот регистрации доктора
    @Transactional
    @PostMapping("/register-doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorAuthDTO doctorRequest) {
        Users user = Mapper.DoctorToUsersEntity(doctorRequest);
        authService.saveDoctor(user);
        Doctor doctor = Mapper.DoctorToDoctorEntity(doctorRequest, user);
        doctorService.save(doctor);
        return new ResponseEntity<>("Doctor registered successfully", HttpStatus.CREATED);
    }

    // Медот регистрации пациента
    @Transactional
    @PostMapping("/register-patient")
    public ResponseEntity<String> registerPatient(@RequestBody @Valid PatientAuthDTO patientRequest) {
        Users user = Mapper.PatientToUsersEntity(patientRequest);
        authService.savePatient(user);
        Patient patient = Mapper.PatientToPatientEntity(patientRequest, user);
        patientService.save(patient);

        return new ResponseEntity<>("Patient registered successfully", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid AdministratorAuthDTO adminRequest) {
        Users user = Mapper.AdminToUsersEntity(adminRequest);
        authService.saveAdmin(user);
        Administrator admin = Mapper.AdminToAdminEntity(adminRequest, user);
        administratorService.save(admin);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginDTO signInRequest){
        return ResponseEntity.ok(authService.authenticateUser(signInRequest));
    }

}
