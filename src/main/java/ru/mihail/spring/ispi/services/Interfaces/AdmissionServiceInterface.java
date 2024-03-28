package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Admission;

import java.util.Date;
import java.util.List;

public interface AdmissionServiceInterface {

    List<Admission> getAdmissionsByDoctorIdAndDate(Long doctorId, Date date);
}
