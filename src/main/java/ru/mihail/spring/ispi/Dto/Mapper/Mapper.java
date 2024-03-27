package ru.mihail.spring.ispi.Dto.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.*;
import ru.mihail.spring.ispi.models.*;
import ru.mihail.spring.ispi.services.Impl.SpecialtyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {


    @Autowired
    private SpecialtyService specialtyService;

    // МАППЕР ДЛЯ АВТОРИЗАЦИИ И РЕГИСТРАЦИИ ДОКТОРА
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
    // МАППЕР ДЛЯ АВТОРИЗАЦИИ И РЕГИСТРАЦИИ ПАЦИЕНТА
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

    // МАППЕР ДЛЯ СПЕЦИАЛЬНОСТИ

    public SpecialtyDTO SpecialtyConvertToDto(Specialty specialty) {
        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setId(specialty.getId());
        specialtyDTO.setName(specialty.getName());
        return specialtyDTO;
    }

    public Specialty SpecialtyConvertToEntity(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyDTO.getId());
        specialty.setName(specialtyDTO.getName());
        return specialty;
    }

    public List<SpecialtyDTO> convertToDtoList(List<Specialty> specialties) {
        return specialties.stream()
                .map(this::SpecialtyConvertToDto)
                .collect(Collectors.toList());
    }

    // МАППЕР ДЛЯ ДОКТОРА
    public List<DoctorDTO> convertToDoctorDTOList(List<Doctor> doctors) {
        return doctors.stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO convertToDoctorDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setUserId(doctor.getUser().getId());
        doctorDTO.setSpecialtyId(doctor.getSpecialty().getId());
        doctorDTO.setPosition(doctor.getPosition());
        return doctorDTO;
    }

    public Doctor convertToDoctorEntity(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());

        Users user = new Users();
        user.setId(doctorDTO.getUserId());
        doctor.setUser(user);

        Specialty specialty = new Specialty();
        specialty.setId(doctorDTO.getSpecialtyId());
        doctor.setSpecialty(specialty);

        doctor.setPosition(doctorDTO.getPosition());
        return doctor;
    }
}
