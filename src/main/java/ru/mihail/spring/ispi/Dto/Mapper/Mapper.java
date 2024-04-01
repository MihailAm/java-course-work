package ru.mihail.spring.ispi.Dto.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.*;
import ru.mihail.spring.ispi.models.*;
import ru.mihail.spring.ispi.services.Impl.DoctorService;
import ru.mihail.spring.ispi.services.Impl.SpecialtyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {


    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private DoctorService doctorService;

    public MedicalService convertToMedicalServiceEntity(MedicalServiceDTO medicalServiceDTO) {
        MedicalService medicalService = new MedicalService();
        medicalService.setId(medicalServiceDTO.getId());
        medicalService.setName(medicalServiceDTO.getName());
        medicalService.setPrice(medicalServiceDTO.getPrice());
        medicalService.setDescription(medicalServiceDTO.getDescription());
        return medicalService;
    }

    public MedicalServiceDTO convertToMedicalServiceDTO(MedicalService medicalService) {
        MedicalServiceDTO medicalServiceDTO = new MedicalServiceDTO();
        medicalServiceDTO.setId(medicalService.getId());
        medicalServiceDTO.setName(medicalService.getName());
        medicalServiceDTO.setPrice(medicalService.getPrice());
        medicalServiceDTO.setDescription(medicalService.getDescription());
        return medicalServiceDTO;
    }



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
        doctor.setOffice(doctorRequest.getOffice());
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
        doctorDTO.setOffice(doctor.getOffice());
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
        doctor.setOffice(doctorDTO.getOffice());
        return doctor;
    }

    // МАППЕР ДЛЯ РАПСАИСАНИЯ

    public Schedule convertToScheduleEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());

        // Преобразование doctorId в объект Doctor, предполагается, что у вас есть сервис для получения доктора по его ID
        Doctor doctor = doctorService.getDoctorById(scheduleDTO.getDoctorId());
        schedule.setDoctor(doctor);

        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setScheduleDate(scheduleDTO.getScheduleDate());
        schedule.setAdditionalInfo(scheduleDTO.getAdditionalInfo());

        return schedule;
    }

    public ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDoctorId(schedule.getDoctor().getId());
        scheduleDTO.setStartTime(schedule.getStartTime());
        scheduleDTO.setEndTime(schedule.getEndTime());
        scheduleDTO.setScheduleDate(schedule.getScheduleDate());
        scheduleDTO.setAdditionalInfo(schedule.getAdditionalInfo());

        return scheduleDTO;
    }

    public List<MedicalServiceDTO> convertToMedicalServiceDTOList(List<MedicalService> medicalServices) {
        return medicalServices.stream()
                .map(this::convertToMedicalServiceDTO)
                .collect(Collectors.toList());
    }

}
