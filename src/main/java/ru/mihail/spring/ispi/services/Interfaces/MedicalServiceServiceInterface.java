package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.MedicalService;
import java.util.List;

public interface MedicalServiceServiceInterface {
    MedicalService createMedicalService(MedicalService medicalService);
    void deleteMedicalService(Long id);
    MedicalService getMedicalServiceById(Long id);
    MedicalService updateMedicalService(MedicalService medicalService);
    List<MedicalService> getAllMedicalServices();

    List<MedicalService> findMedicalServiceByName(String name);
}
