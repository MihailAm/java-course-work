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


    // МАППЕР ДЛЯ АВТОРИЗАЦИИ И РЕГИСТРАЦИИ админа
    public Users AdminToUsersEntity(AdministratorAuthDTO authDTO) {
        Users user = new Users();
        user.setEmail(authDTO.getEmail());
        user.setPassword(authDTO.getPassword());
        return user;
    }

    public Administrator AdminToAdminEntity(AdministratorAuthDTO authDTO, Users user) {
        Administrator administrator = new Administrator();
        administrator.setUser(user);
        administrator.setFirstName(authDTO.getFirstName());
        administrator.setLastName(authDTO.getLastName());

        return administrator;
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
        doctor.setWork_experience(doctorRequest.getWork_experience());
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
        doctorDTO.setWork_experience(doctor.getWork_experience());
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
        doctor.setWork_experience(doctorDTO.getWork_experience());
        return doctor;
    }

    // МАППЕР ДЛЯ РАСПИСАНИЯ

    public Schedule convertToScheduleEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());

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

    public Patient convertToPatientEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAge(patientDTO.getAge());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setSnils(patientDTO.getSnils());
        // Установка user
        Users user = new Users();
        user.setId(patientDTO.getUserId());
        patient.setUser(user);

        return patient;
    }

    public PatientDTO convertToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setUserId(patient.getUser().getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setAge(patient.getAge());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setSnils(patient.getSnils());

        return patientDTO;
    }

    public void updatePatientFromDTO(Patient existingPatient, PatientDTO updatedPatientDTO) {
        existingPatient.setFirstName(updatedPatientDTO.getFirstName());
        existingPatient.setLastName(updatedPatientDTO.getLastName());
        existingPatient.setAge(updatedPatientDTO.getAge());
        existingPatient.setPhoneNumber(updatedPatientDTO.getPhoneNumber());
        existingPatient.setSnils(updatedPatientDTO.getSnils());
    }

    public List<PatientDTO> convertToPatientDTOList(List<Patient> patients) {
        return patients.stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    public MedicalReportDTO convertToMedicalReportDTO(MedicalReport medicalReport) {
        MedicalReportDTO medicalReportDTO = new MedicalReportDTO();
        medicalReportDTO.setId(medicalReport.getId());
        medicalReportDTO.setPatientId(medicalReport.getPatient().getId());
        medicalReportDTO.setDoctorId(medicalReport.getDoctor().getId());
        medicalReportDTO.setAdmissionId(medicalReport.getAdmission().getId());
        medicalReportDTO.setReport(medicalReport.getReport());
        return medicalReportDTO;
    }

    public List<MedicalReportDTO> convertToMedicalReportDTOList(List<MedicalReport> medicalReports) {
        return medicalReports.stream()
                .map(this::convertToMedicalReportDTO)
                .collect(Collectors.toList());
    }

    public MedicalReport convertToMedicalReportEntity(MedicalReportDTO medicalReportDTO) {
        MedicalReport medicalReport = new MedicalReport();

        medicalReport.setId(medicalReportDTO.getId());
        medicalReport.setReport(medicalReportDTO.getReport());

        Doctor doctor = new Doctor();
        doctor.setId(medicalReportDTO.getDoctorId());
        medicalReport.setDoctor(doctor);

        Patient patient = new Patient();
        patient.setId(medicalReportDTO.getPatientId());
        medicalReport.setPatient(patient);

        Admission admission = new Admission();
        admission.setId(medicalReportDTO.getAdmissionId());
        medicalReport.setAdmission(admission);

        return medicalReport;
    }

    public Admission convertToAdmissionEntity(AdmissionDTO admissionDTO) {
        Admission admission = new Admission();
        admission.setId(admissionDTO.getId());

        Doctor doctor = new Doctor();
        doctor.setId(admissionDTO.getDoctorId());
        admission.setDoctor(doctor);

        Patient patient = new Patient();
        patient.setId(admissionDTO.getPatientId());
        admission.setPatient(patient);

        admission.setDate(admissionDTO.getDate());
        admission.setTime(admissionDTO.getTime());

        MedicalService service = new MedicalService();
        service.setId(admissionDTO.getServiceId());
        admission.setService(service);

        return admission;
    }

    public AdmissionDTO convertToAdmissionDTO(Admission createdAdmission) {
        AdmissionDTO admissionDTO = new AdmissionDTO();
        admissionDTO.setId(createdAdmission.getId());

        if (createdAdmission.getDoctor() != null) {
            admissionDTO.setDoctorId(createdAdmission.getDoctor().getId());
        }

        if (createdAdmission.getPatient() != null) {
            admissionDTO.setPatientId(createdAdmission.getPatient().getId());
        }

        admissionDTO.setDate(createdAdmission.getDate());
        admissionDTO.setTime(createdAdmission.getTime());
        admissionDTO.setServiceId(createdAdmission.getService().getId());

        return admissionDTO;
    }

    public List<AdmissionDTO> convertToAdmissionDTOList(List<Admission> admissions) {
        return admissions.stream()
                .map(this::convertToAdmissionDTO)
                .collect(Collectors.toList());
    }

    public ScheduleDoctorDTO convert(Admission admission) {
        ScheduleDoctorDTO scheduleDoctorDTO = new ScheduleDoctorDTO();
        scheduleDoctorDTO.setId(admission.getId());
        scheduleDoctorDTO.setDoctorFirstName(admission.getDoctor().getFirstName());
        scheduleDoctorDTO.setDoctorLastName(admission.getDoctor().getLastName());
        scheduleDoctorDTO.setPatientFirstName(admission.getPatient().getFirstName());
        scheduleDoctorDTO.setPatientLastName(admission.getPatient().getLastName());
        scheduleDoctorDTO.setDate(admission.getDate().toString());
        scheduleDoctorDTO.setTime(admission.getTime());

        return scheduleDoctorDTO;
    }
}
