package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Patient;

import java.util.List;

public interface PatientServiceInterface {

    void save(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient createPatient(Patient patient);

    Patient updatePatient(Long id, Patient updatedPatient);

    void deletePatient(Long id);

    //String viewMedicalRecord(Long id);
}
