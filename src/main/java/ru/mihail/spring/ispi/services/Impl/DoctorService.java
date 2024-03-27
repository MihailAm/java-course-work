package ru.mihail.spring.ispi.services.Impl;


import ch.qos.logback.core.testUtil.MockInitialContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.repositories.DoctorRepository;
import ru.mihail.spring.ispi.repositories.UserRepository;
import ru.mihail.spring.ispi.services.Interfaces.DoctorServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService implements DoctorServiceInterface {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        if (doctorRepository.existsById(doctor.getId())) {
            return doctorRepository.save(doctor);
        }
        return null;
    }

    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor =  doctorRepository.findById(id).orElse(null);
        doctorRepository.deleteById(id);
        userRepository.deleteById(doctor.getUser().getId());
    }

    @Override
    public List<Doctor> searchDoctorsBySpecialty(String specialtyName) {
        return doctorRepository.findBySpecialtyName(specialtyName);
    }


}
