package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.models.Patient;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface AdmissionServiceInterface {

    List<Admission> getAllAdmissions();

    Admission createAdmission(Admission admission);

    Admission getAdmissionById(Long id);

    void deleteAdmissionById(Long id);

    Admission updateAdmission(Admission admission);

    List<Admission> findAdmissionByPatient(Patient patient);

    List<Admission> getAdmissionsByDoctorIdAndDate(Long doctorId, Date date);

    List<Admission> findByDoctorIdDateTime(Long doctorId, Date date, LocalTime time);

    boolean isTimeSlotTaken(Date date, LocalTime time);
}
