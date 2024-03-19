package ru.mihail.spring.ispi.Dto.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.*;
import ru.mihail.spring.ispi.models.*;
import ru.mihail.spring.ispi.services.SpecialtyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mapper {

    // МАППЕР ДЛЯ ДОКТОРА ПРИ ВХОДЕ
    @Autowired
    private SpecialtyService specialtyService;

    public Users DoctorToUsersEntity(DoctorAuthDTO doctorRequest) {
        Users user = new Users();
        user.setEmail(doctorRequest.getEmail());
        user.setPassword(doctorRequest.getPassword());
        return user;
    }

    public Doctor DoctorToDoctorEntity(DoctorAuthDTO doctorRequest, Users user) {
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPosition(doctorRequest.getPosition());
        Specialty specialty = specialtyService.findById(doctorRequest.getSpecialtyId());
        doctor.setSpecialty(specialty);
        return doctor;
    }
    public Users PatientToUsersEntity(PatientAuthDTO patientRequest) {
        Users user = new Users();
        user.setEmail(patientRequest.getEmail());
        user.setPassword(patientRequest.getPassword());
        return user;
    }

    public Patient PatientToPatientEntity(PatientAuthDTO patientRequest, Users user) {
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        patient.setAge(patientRequest.getAge());
        patient.setPhoneNumber(patientRequest.getPhoneNumber());
        patient.setSnils(patientRequest.getSnils());
        return patient;
    }
}
