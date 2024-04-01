package ru.mihail.spring.ispi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.MedicalService;
import ru.mihail.spring.ispi.repositories.MedicalServiceRepository;
import ru.mihail.spring.ispi.services.Interfaces.MedicalServiceServiceInterface;

import java.util.List;

@Service
public class MedicalServiceService implements MedicalServiceServiceInterface {

    @Autowired
    private MedicalServiceRepository medicalServiceRepository;

    @Override
    public MedicalService addMedicalService(MedicalService medicalService) {
        return medicalServiceRepository.save(medicalService);
    }

    @Override
    public void deleteMedicalService(Long id) {
        medicalServiceRepository.deleteById(id);
    }

    @Override
    public MedicalService getMedicalServiceById(Long id) {
        return medicalServiceRepository.findById(id).orElse(null);
    }

    @Override
    public MedicalService updateMedicalService(MedicalService medicalService) {
        if (medicalServiceRepository.existsById(medicalService.getId())) {
            return medicalServiceRepository.save(medicalService);
        }
        return null;
    }

    @Override
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceRepository.findAll();
    }

    @Override
    public List<MedicalService> findMedicalServiceByName(String name) {
        return medicalServiceRepository.findByName(name);
    }
}
