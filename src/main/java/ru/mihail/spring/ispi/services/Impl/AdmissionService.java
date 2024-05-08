package ru.mihail.spring.ispi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.repositories.AdmissionRepository;
import ru.mihail.spring.ispi.services.Interfaces.AdmissionServiceInterface;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class AdmissionService implements AdmissionServiceInterface {
    @Autowired
    private AdmissionRepository admissionRepository;

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission createAdmission(Admission admission) {
        return admissionRepository.save(admission);
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAdmissionById(Long id) {
        admissionRepository.deleteById(id);
    }

    public Admission updateAdmission(Admission admission){
        if (admissionRepository.existsById(admission.getId())) {
            return admissionRepository.save(admission);
        }
        return null;
    }

    @Override
    public List<Admission> findAdmissionByPatient(Patient patient) {
        return admissionRepository.findByPatient(patient);
    }

    // ЭТО НЕ УДАЛЯТЬ !!!!!!!!
    public List<Admission> getAdmissionsByDoctorIdAndDate(Long doctorId, Date date) {
        return admissionRepository.findByDoctorIdAndDate(doctorId, date);
    }

    public List<Admission> findByDoctorIdDateTime(Long doctorId, Date date, LocalTime time){
        return admissionRepository.findByDoctorIdAndDateAndTime(doctorId, date, time);
    }

}
