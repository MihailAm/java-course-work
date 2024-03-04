package ru.mihail.spring.ispi.services;


import ch.qos.logback.core.testUtil.MockInitialContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.repositories.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    // Заглушка для получения врача по его идентификатору (id)
    public Doctor getDoctorById(Long id) {
        // Реальная логика получения врача по id может быть добавлена здесь
        // Возвращаем заглушечный объект Doctor
        return new Doctor();
    }

    // Заглушка для получения списка всех врачей
    public List<Doctor> getAllDoctors() {
        // Логика получения всех врачей
        return new ArrayList<>();
    }

    // Заглушка для обновления врача по id
    public Doctor updateDoctor(Doctor doctor) {
        // Логика обновления врача
        return new Doctor();
    }

    // Заглушка для удаления врача по id
    public void deleteDoctorById(Long id) {
        // Логика удаления врача
    }

    // Заглушка для поиска врачей по специальности
    public List<Doctor> searchDoctorsBySpecialty(String specialtyName) {
        // Логика поиска врачей по специальности

        return new ArrayList<>();
    }
}
