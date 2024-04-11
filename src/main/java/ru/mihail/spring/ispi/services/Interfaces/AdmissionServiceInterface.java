package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Admission;

import java.util.Date;
import java.util.List;

public interface AdmissionServiceInterface {

    List<Admission> getAllAdmissions();

    Admission createAdmission(Admission admission);

    Admission getAdmissionById(Long id);

    void deleteAdmissionById(Long id);

    Admission updateAdmission(Admission admission);

    List<Admission> getAdmissionsByDoctorIdAndDate(Long doctorId, Date date);
}
