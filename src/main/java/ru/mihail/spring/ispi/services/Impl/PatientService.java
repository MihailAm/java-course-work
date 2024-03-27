package ru.mihail.spring.ispi.services.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {


    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        //return patientRepository.findAll();
        return null; //tmp
    }

    public Patient getPatientById(Long id) {
        //Optional<Patient> optionalPatient = patientRepository.findById(id);
        //return optionalPatient.orElse(null);
        return null;  //tmp
    }

    public Patient createPatient(Patient patient) {
        // Логика проверок перед сохранением, если необходимо
        //return patientRepository.save(patient);
        return patient;  //tmp
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        /*
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();

            // Обновление полей пациента
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
            existingPatient.setSnils(updatedPatient.getSnils());
            // Обновление других полей по необходимости

            return patientRepository.save(existingPatient);
        } else {
            return null; // Запись не найдена
        }
        */
        return updatedPatient;  //tmp
    }

    public boolean deletePatient(Long id) {
        /*
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
            return true;
        } else {
            return false; // Запись не найдена
        }
        */
        return false;  //tmp
    }

    public String viewMedicalRecord(Long id) {
        // Логика для просмотра медкарты
        // Возвращает строку с медицинской информацией (здесь это просто пример)
        return "Medical record for patient with ID " + id;  //tmp
    }
}

