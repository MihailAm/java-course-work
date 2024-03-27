package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Doctor;

import java.util.List;

public interface DoctorServiceInterface {
    void save(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctorById(Long id);
    List<Doctor> searchDoctorsBySpecialty(String specialtyName);
}
