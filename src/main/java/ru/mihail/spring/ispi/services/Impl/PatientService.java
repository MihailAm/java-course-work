package ru.mihail.spring.ispi.services.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.repositories.PatientRepository;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;
import ru.mihail.spring.ispi.services.Interfaces.PatientServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientServiceInterface {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElse(null);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
            existingPatient.setSnils(updatedPatient.getSnils());
            return patientRepository.save(existingPatient);
        } else {
            return null; // Запись не найдена
        }
    }

    public boolean deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
            return true;
        } else {
            return false; // Запись не найдена
        }
    }

    /*
    public String viewMedicalRecord(Long id) {
        // Логика для просмотра медкарты
        // Возвращает строку с медицинской информацией (здесь это просто пример)
        return "Medical record for patient with ID " + id;  //tmp
    }
     */
}
